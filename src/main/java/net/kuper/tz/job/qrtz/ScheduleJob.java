/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package net.kuper.tz.job.qrtz;


import lombok.extern.slf4j.Slf4j;
import net.kuper.tz.core.utils.SpringUtils;
import net.kuper.tz.core.utils.StringUtils;
import net.kuper.tz.job.entity.JobEntity;
import net.kuper.tz.job.entity.JobLogUpdateEntity;
import net.kuper.tz.job.service.JobLogService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.2.0 2016-11-28
 */
@Slf4j
public class ScheduleJob extends QuartzJobBean {

	/**
	 * 任务调度参数key
	 */
	public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

	private ExecutorService service = Executors.newSingleThreadExecutor(); 
	
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobEntity jobEntity = (JobEntity) context.getMergedJobDataMap().get(JOB_PARAM_KEY);
        
        //获取spring bean
		JobLogService jobLogService = (JobLogService) SpringUtils.getBean("jobLogService");
        
        //数据库保存执行记录
        JobLogUpdateEntity logUpdateEntity = new JobLogUpdateEntity();
        logUpdateEntity.setJobId(jobEntity.getId());
        logUpdateEntity.setBeanName(jobEntity.getBeanName());
        logUpdateEntity.setMethodName(jobEntity.getMethodName());
        logUpdateEntity.setParams(jobEntity.getParams());
        logUpdateEntity.setCreateTime(new Date());
        
        //任务开始时间
        long startTime = System.currentTimeMillis();
        
        try {
            //执行任务
        	log.info("任务准备执行，任务ID：" + jobEntity.getId());
            ScheduleRunnable task = new ScheduleRunnable(jobEntity.getBeanName(),jobEntity.getMethodName(), jobEntity.getParams());
            Future<?> future = service.submit(task);
			future.get();
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			logUpdateEntity.setTimes((int)times);
			//任务状态    0：成功    1：失败
			logUpdateEntity.setStatus(0);
			log.info("任务执行完毕，任务ID：" + jobEntity.getId() + "  总共耗时：" + times + "毫秒");
		} catch (Exception e) {
			log.error("任务执行失败，任务ID：" + jobEntity.getId(), e);
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			logUpdateEntity.setTimes((int)times);
			
			//任务状态    0：成功    1：失败
			logUpdateEntity.setStatus(1);
			logUpdateEntity.setError(StringUtils.substring(e.toString(), 0, 2000));
		}finally {
			jobLogService.save(logUpdateEntity);
		}
    }
}
