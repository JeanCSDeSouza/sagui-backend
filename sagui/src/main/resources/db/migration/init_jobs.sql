INSERT INTO `sagui`.`scheduler_job_info` (`cron_expression`, `job_class`,
  `job_group`, `job_name`, `cron_job`, `repeat_time`)
  VALUES ('0 0/5 * ? * *', 'br.com.unirio.sagui.pdfjobs.SampleCronJob',
    'Test_Cron', 'Sample Cron', '', NULL)