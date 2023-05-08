# ESPN Newsletter

In 2021, ESPN discontinued their newsletter. I, as an avid fan of both sports and newsletters decided to create my own. This is my attempt at replicating a halfway-decent sports newsletter by scraping the ESPN RSS feed and bundling it all together into one daily email. Also, this was done so I could learn Clojure.

### Environment Variables
The following environment variables need to be set in order to run the script:
- `:smtp-email`: The username to login to the SMTP server (currently only supports Gmail)
- `:smtp-password`: The password for the SMTP server login (use an app password for Gmail)
- `:from-email`: Email to send from
- `:to-email`: Email to send to

