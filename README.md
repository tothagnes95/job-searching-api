# job-searching-api

I developed a job searching application that aggregates data from multiple sources to provide comprehensive job listings. One of the primary data sources is The Muse API, which offers job opportunities from various companies. In addition to that, I've established a custom SQL database where clients can register and post new jobs directly. Users of the application can search for jobs based on job descriptions and locations across both the API and the in-house SQL database. 

To initiate the application, a MySQL database is required. The application connects to this database using DB_USERNAME and DB_PASSWORD credentials. For testing purposes, an H2 database has been configured.

To further enhance the project, the following steps are planned:
- Finish WireMock: Complete the implementation of WireMock for integration testing.
- Review: Conduct a thorough review of the current codebase and functionalities to identify areas of improvement and potential issues.
- Refactor: Reorganize and optimize the code for better maintainability, performance, and readability.
- Docker Integration: Containerize the application using Docker, which will ensure consistent environments, ease of deployment, and scalability.
- Continuous Integration (CI): Set up a CI pipeline to automatically build and test the application whenever code changes are introduced.
- Continuous Deployment (CD): Implement a CD process to automate the release of the containerized application, ensuring quick and reliable deliveries.
- Connect with a Cloud Provider: Integrate the application with a cloud provider to leverage scalable infrastructure and enhanced availability.
