alter table `RAD_AWS_DEV`.`rad_provider_info` 
   add column `PROVIDER_PATIENT_CAT` int(10) NULL after `UPDATED_DATE`, 
   add column `PROVIDER_GENDER` char(1) NULL after `PROVIDER_PATIENT_CAT`,
   change `PROVIDER_NOTES` `PROVIDER_NOTES` varchar(255) character set utf8 collate utf8_general_ci NULL 