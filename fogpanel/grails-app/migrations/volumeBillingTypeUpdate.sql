UPDATE volume SET billing_type ="hourly";
UPDATE snapshot SET billing_type ="hourly";
UPDATE config set value = "10" where name = "MAX_LOGIN_FAILURE"; 
UPDATE config set value = "5" where name = "MAX_LOGIN_FAILURE_PER_IP"; 
UPDATE config set value = "10" where name = "ACCOUNT_UNLOCK_TIME"; 
UPDATE config set value = "10" where name = "LINK_ACTIVE_TIME";