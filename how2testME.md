This document will provide you the guidance how to pass the acceptance criteria. 



Part 1.
Here i have worked with two approaches. 
Approach A. 
 Request mapping is as per test assignment. http://localhost:5000/short?url=<long url> and http://localhost:5000/long?url=<tiny representation of url>. In this approach the tiny-rep-of-url is not   that short. So i also worked with approach B.
 
Approach B. 
 As we cannot go back from hashcode (as two source objects can end up with same hashcode value). 
 So i have a custom mapping approach to return something short. 
 However if we run multiple times it is giving same tiny url (even if long url is changed) again and again. But it is fine when we run only once.
 
 Here are the Request mappings:
 http://localhost:5000/short-url?url=<long url> and http://localhost:5000/long-url?url=<tiny representation of url>

Part 2. 
  I am doing the persistence with file h2 database. In-memory H2 implementation will not give me required results.
 

Part 3. 
  Scheduler is running and deleting all records older than 30 mintues time. I am running the scheduler every 1 minute.

