# pylint: disable=relative-beyond-top-level
from fastapi.testclient import TestClient
from main import app

client = TestClient(app)

# Create a new user in database and get the same user
def test_create_and_get_user():
    response = client.post(
        "/users/",
        json={
            "username": "test_user@nodomain.com",
            "advocate": "true",
            "biography": "Text 1",
            "birthdate": "00-00-0000",
            "firstname": "Test",
            "lastname": "User",
            "oabnumber": "No Number",
            "areasofexpertise": [
                "No Area"
            ],
            "phone": "+55 11 99999-9999",
            "password": "test@test"
        },
    )
    #Check response
    assert response.status_code == 200, response.text
    data = response.json()
    assert data["username"] == "test_user@nodomain.com"
    assert data["advocate"] == True
    assert data["biography"] == "Text 1"
    assert data["birthdate"] == "00-00-0000"
    assert data["firstname"] == "Test"
    assert data["lastname"] == "User"
    assert data["oabnumber"] == "No Number"
    assert data["areasofexpertise"] == ['No Area']
    assert data["phone"] == "+55 11 99999-9999"
    assert "uuid" in data
    user_uuid = data["uuid"]
    assert "created" in data
    user_created = data["created"]
    #Get the user already created in database
    response = client.get(f"/users/{user_uuid}/")
    assert response.status_code == 200, response.text
    assert data["username"] == "test_user@nodomain.com"
    assert data["advocate"] == True
    assert data["biography"] == "Text 1"
    assert data["birthdate"] == "00-00-0000"
    assert data["firstname"] == "Test"
    assert data["lastname"] == "User"
    assert data["oabnumber"] == "No Number"
    assert data["areasofexpertise"] == ['No Area']
    assert data["phone"] == "+55 11 99999-9999"
    assert data["uuid"] == user_uuid
    assert data["created"] == user_created

# Create a duplicated user in database
def test_create_duplicated_user():
    response = client.post(
        "/users/",
        json={
            "username": "test_user@nodomain.com",
            "advocate": "true",
            "biography": "Text 1",
            "birthdate": "00-00-0000",
            "firstname": "Test",
            "lastname": "User",
            "oabnumber": "No Number",
            "areasofexpertise": [
                "No Area"
            ],
            "phone": "+55 11 99999-9999",
            "password": "test@test"
        },
    )
    #Check response
    assert response.status_code == 400, response.text
    data = response.json()
    assert data["detail"] == "Username already registered"

# Create a user without a mandatory value in database
def test_create_wrong_user():
    response = client.post(
        "/users/",
        json={
            "username": "test_user@nodomain.com",
            "advocate": "true",
            "biography": "Text 1",
            "birthdate": "00-00-0000",
            "firstname": "Test",
            "lastname": "User",
            "oabnumber": "No Number",
            "areasofexpertise": [
                "No Area"
            ],
            "phone": "+55 11 99999-9999"
        },
    )
    #Check response
    assert response.status_code == 422, response.text
    data = response.json()
    assert data["detail"] == [{'loc': ['body', 'password'], 'msg': 'field required', 'type': 'value_error.missing'}]

#Get wrong user in database
def test_get_wrong_user():
    response = client.get("/users/1")
    #Check response
    assert response.status_code == 404, response.text
    data = response.json()
    assert data["detail"] == "User not found"

#Get all users in database
def test_get_users():
    response = client.get("/users/?skip=0&limit=100")
    #Check response
    assert response.status_code == 200, response.text
    data = response.json()

#Update an user in database
def test_update_user():
    response = client.get(f"/logins/test_user@nodomain.com/")
    assert response.status_code == 200, response.text
    data = response.json()
    user_uuid = data["uuid"]
    print(user_uuid)

    response = client.put(
       f"/users/{user_uuid}",
       json={
           "username": "test_user@nodomain.com",
           "advocate": "true",
           "biography": "Text 2",
           "birthdate": "00-00-0000",
           "firstname": "Test",
           "lastname": "User",
           "oabnumber": "No Number",
           "areasofexpertise": [
               "No Area"
           ],
           "phone": "+55 11 98888-8888",
           "password": "test@test"
        },
    )
    assert response.status_code == 200, response.text
    data = response.json()
    assert data["username"] == "test_user@nodomain.com"
    assert data["advocate"] == True
    assert data["biography"] == "Text 2"
    assert data["birthdate"] == "00-00-0000"
    assert data["firstname"] == "Test"
    assert data["lastname"] == "User"
    assert data["oabnumber"] == "No Number"
    assert data["areasofexpertise"] == ['No Area']
    assert data["phone"] == "+55 11 98888-8888"
    assert "uuid" in data
    user_uuid = data["uuid"]
    assert "created" in data
    user_created = data["created"]

    response = client.get(f"/users/{user_uuid}/")
    assert response.status_code == 200, response.text
    assert data["username"] == "test_user@nodomain.com"
    assert data["advocate"] == True
    assert data["biography"] == "Text 2"
    assert data["birthdate"] == "00-00-0000"
    assert data["firstname"] == "Test"
    assert data["lastname"] == "User"
    assert data["oabnumber"] == "No Number"
    assert data["areasofexpertise"] == ['No Area']
    assert data["phone"] == "+55 11 98888-8888"
    assert data["uuid"] == user_uuid
    assert data["created"] == user_created
