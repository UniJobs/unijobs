# UniJobs
Welcome to UniJobs


ChangeLog 10-Jan-2018
    * Added pagination - each request now takes a page number; the page size is set to 10 by default (it can be changed)
    * Improved query for getting al jobs for a user (based on the skills) - since it is a native query, it does not support
        pagination so a workaround had to be made

ChangeLog 14-Nov-2017 - PAY ATTENTION!
* Users representation has changed; clients and providers do not exist anymore; they are both UniUsers
* The PRELOAD files have changed; there is a "Skills.csv" file now which contains all the skills that a User might have
    (or all the tags a job might have)
    There is a "UniUsers.csv" file (randomly generated) which has the following format:
    - username, password, email, first name, last name, date of birth, phone number, <bold>NEW</bold> - a list of skills
    that can be found in Skills.csv which the user has - semicolon separated, number of jobs created by the user - then for each job:, job description, job location,
    hours per week, earnings, start date, end date, list of required skills - semicolon separated
    
    <bold> Due to these changes, a new preload of the database is mandatory before using the application !!! </bold>
* There is an api endpoint /getAllJobsForUser/{userId} which returns all the jobs which have at least one tag(skill) which can be found in the user's
    list of skills

Preload:
* Responds to request "doPreload"
* MAKE SURE THAT YOUR DATABASE IS EMPTY !!!
* Add Users, Skills and Jobs (necessary entities for the majority of the filters)



