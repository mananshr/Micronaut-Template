# Micronaut CRUD API Documentation

## Authentication Endpoints

### Sign Up

- **URL**: `/auth/signup`
- **Method**: `POST`
- **Description**: Register a new user
- **Request Body**:

```json
{
    "email": "user@example.com",
    "name": "John Doe",
    "password": "securepassword123"
}
```

- **Response**:

```json
{
    "id": "user123",
    "email": "user@example.com",
    "name": "John Doe",
    "auth0Id": "auth0|123456789"
}
```

- **Curl Example**:

```bash
curl -X POST http://localhost:8080/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "name": "John Doe",
    "password": "securepassword123"
  }'
```

### Login

- **URL**: `/auth/login`
- **Method**: `POST`
- **Description**: Authenticate user and get token
- **Request Body**:

```json
{
    "email": "user@example.com",
    "password": "securepassword123"
}
```

- **Response**:

```json
{
    "access_token": "eyJ0eXAi...",
    "token_type": "Bearer",
    "expires_in": 86400
}
```

- **Curl Example**:

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "securepassword123"
  }'
```

## Protected Endpoints

### Get User Profile

- **URL**: `/auth/profile`
- **Method**: `GET`
- **Description**: Get authenticated user's profile
- **Authentication**: Bearer token required
- **Response**:

```json
{
    "id": "user123",
    "email": "user@example.com",
    "name": "John Doe"
}
```

- **Curl Example**:

```bash
curl -X GET http://localhost:8080/auth/profile \
  -H "Authorization: Bearer eyJ0eXAi..."
```

## Error Responses

### Common Error Format

```json
{
    "message": "Error description",
    "status": 400,
    "timestamp": "2024-01-20T14:30:00Z"
}
```

### Common Status Codes

- 200: Success
- 201: Created
- 400: Bad Request
- 401: Unauthorized
- 403: Forbidden
- 404: Not Found
- 500: Internal Server Error

## Authentication Notes

1. All tokens are JWT format
2. Tokens expire after 24 hours
3. Include token in Authorization header for protected endpoints
4. Auth0 is used as the authentication provider

## Rate Limiting

- 100 requests per minute per IP
- 1000 requests per hour per user
