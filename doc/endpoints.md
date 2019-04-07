Description of User object :
* Fields required :
	* username (primary key)
	* password 
	* email 
	* firstname
	* lastname 


| **Endpoints for User** | **Method type** | **Input**                                         | **Output**                                                                    | **Description**                                                                                                                                                                                                                                                                |
|:----------------------:|-----------------|---------------------------------------------------|-------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| /api/users             | **Get**         | takes no input                                    | returns LIST of all existing users in DB                                      | This endpoint is used to return a list of every User that exists in the DB                                                                                                                                                                                                     |
| /api/user/{user_name}  | **Get**         | takes "user name" as input through url            | returns an User object having user name = user_name                           | This endpoint is used to find a specific user based on their identifying attribute (which is their user name)                                                                                                                                                                  |
| /api/user              | **post**        | takes as input JSON of type User in request body. | returns the same JSON back if success.                                        | This endpoint is used to add a new User to the DB                                                                                                                                                                                                                              |
| /api/user/{user_name}  | **Delete**      | takes "user name" as input through url            | returns as String : "successfully deleted user with user name = " + user_name | This endpoint is used to delete a specific User in the DB according to the given user_name in the url                                                                                                                                                                          |
| /api/user              | **Delete**      | takes as input JSON of type User in request body. | returns as String : "successfully deleted user with user name = " + user_name | This endpoint is used to delete a specific User in the DB according to the given user_name which is stated in the JSON body. It is similar to the delete mapping above but in this one, a JSON object is required in the request message of the user that needs to be deleted. |


Description of UserCareer Object :
* Fields required 
	* username (primary key, reffers to user.username)
	* co2saved 




| **Endpoints for UserCareer** | **Method type** | **Input**                                                  | **Output**                                                                                                                 | **Description**                                                                                                     |
|:----------------------------:|-----------------|------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|
| /api/career                  | Get             | takes no input                                             | returns LIST of all existing user careers in DB                                                                            | This endpoint is used to return a list of every UserCareer(s) that exists in the DB                                 |
| /api/career/{user_name}      | Get             | takes "user name" as input through url                     | returns a UserCareer object having user name = user_name                                                                   | This endpoint is used to find a specific records based on their identifying attribute (which is their user name) |
| /api/career                  | Post            | takes as input JSON of type UserCareer in request body.    | returns the same JSON back if success.                                                                                     | This endpoint is used to add a new UserCareer to the DB                                                             |
| /api/career                  | Delete          | takes as input JSON of type UserCareer in the request body | returns the following String if success : "successfully deleted career for user with user name = " + career.getUserName(); | This endpoint is used to delete a specific records according to the UserName given in the JSON object.           |


Description of FoodEmission object :
* Fields required 
	* food (this is the foodName, it is the primary key)
	* emission (emission of a given food)
	* miles (car miles)



|  *Endpoints for FoodEmission* | **Method type** | **Input**                                                      | **Output**                                                        | **Description**                                                                    |
|:-----------------------------:|-----------------|----------------------------------------------------------------|-------------------------------------------------------------------|------------------------------------------------------------------------------------|
| /api/foodEmission             | Get             | takes no input                                                 | returns LIST of all existing FoodEmission objects in DB           | This endpoint is used to return a list of every FoodEmission that exists in the DB |
| /api/foodEmission/{food_name} | Get             | takes as input food_name from the url                          | returns a specific foodEmission object where foodName = food_name | This endpoint is used to return an object of a specific FoodEmission from the DB   |
| /api/foodEmission             | post            | takes as input a JSON of type foodEmission in the request body | returns the same JSON back if success                             | This endpoint is used to add a new FoodEmission entry to the DB                    |