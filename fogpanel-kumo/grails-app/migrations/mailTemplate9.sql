UPDATE mail_template
SET content="<div id='footer' style='width:600px; height:auto; float:left; background:#f2f2f2; margin:15px 0 0 0; padding:0;'><div class='footer_content' style='width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;'><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0	0;padding:0;height:auto;width:550px'>This email message is related to your ongoing services ,  messages contain important information related to the administration and smooth running of your account and cannot be unsubscribed.</p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>For further assistance, please contact our support desk by <br />xxxxxxx<br />Copyright © xxxx<br /></p></div></div>"
WHERE id=6;

UPDATE config
SET value = 100
WHERE id= 44;

UPDATE config
SET value= 1312
WHERE id= 43;
