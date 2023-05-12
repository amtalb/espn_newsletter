# ESPN Newsletter

In 2021, ESPN discontinued their newsletter. I, as an avid fan of both sports and newsletters decided to create my own. This is my attempt at replicating a halfway-decent sports newsletter by scraping the ESPN RSS feed and bundling it all together into one daily email. Also, this was done so I could learn Clojure.

### Setup
This script runs in an AWS Lambda function daily. To set it up on your own AWS account first, compile the code into an uberJAR:
```
lein uberjar
```
Then, either through the CLI or the Console, create a Lambda function and upload the standalone uberJAR (created in the `target` folder) as code. From there, set the runtime to Java 8, set the environment variables, and choose a trigger; mine is set to 7AM daily so I can wake up to the world of sports!  

### Environment Variables
The following environment variables need to be set in order to run the script:
- `SMTP_EMAIL`: The username to login to the SMTP server (currently only supports Gmail)
- `SMTP_PASSWORD`: The password for the SMTP server login (use an app password for Gmail)
- `FROM_EMAIL`: Email to send from
- `TO_EMAIL`: Email to send to

