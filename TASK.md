# MovieDB application

Application that stores info about movies, users and their reviews.

Movie entity:
* Name (unique), issueDate, rating (double).

User entity:
* FirstName, lastName, age, username (unique).

MovieReview entity:
* User, movie, rating (integer from 1 to 10), comment.

You have 2 main services: we can call them gateway-service and aggregator-service.
Gateway-service is the main entrance of your application. Gateway-service does not have access to your main AWS storage (where all data is stored), however it can call some caching storage for some GET operations. It can call aggregator-service to retrieve data, it can call any AWS service to put data. It supports the following operations:
* GET – top 10 movies with the highest rating
* GET – user info by username - DONE
* GET – top 10 favorite user movies by username
* GET – movie rating by movie name - DONE
* POST – create new movie - DONE
* POST – create new user - DONE
* POST – add new movie review by user - DONE
* DELETE – delete user (all user reviews should be also deleted in 10 minutes)

Customer requirements:
 - Each new movie review should affect movie rating in 5 minutes.
 - You can receive up 200 movie reviews per second.
 - Load balancing should be supported.
 - All movie rating calculation logic should be placed inside aggregator-service.

Requirements update (phase 2):

Your customer recently decided to integrate with the similar resource (let’s call it CinemaDB). So you need to be able to support batch update of your storage. CinemaDB team can send you JSON file with all the necessary information about movies, users and reviews. They will structure data as you want it. They will call your system from time to time. However, your system (gateway-service and aggregator-service) is already overloaded with the already created logic, so you can’t use them for this batch update from CinemaDB team. All rating calculation logic still should be placed inside aggregator-service.

Recommended AWS resources:
EC2, Load Balancer, RDS, SQS, Lambda, API Gateway, DynamoDB ElastiCache
