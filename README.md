# disneydemo
Alkemy Challenge

- Springboot
- SpringREST
- Spring Security (CSRF Token configured for Post-Man access and Swagger)
- Login Auth. Data encrypted with Bcrypt.
- MySQL tables created manually
- Hibernate ORM
- Registration Verification Mail.
  JSONPatch.

-------
Notes: 
- Token is sent in the HTTP request. To access HTTP Methods from
Postman the X-XSRF-TOKEN must be set up in the header and as an 
environment value.<br>
https://www.youtube.com/watch?v=kRGJraWoCxk

- Patch Request done using JSONPatch. 
https://www.baeldung.com/spring-rest-json-patch
<br>Example: modifying Genre name.
  [{
  "op": "replace",
  "path": "/name",
  "value":"Thriller"
  }]

