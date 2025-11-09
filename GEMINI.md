### Development Plan

1.  **Part 1: Project Setup and Data Model**
    *   Configure `application.properties` for the H2 database.
    *   Create the `Categoria` enum for product categories.
    *   Define the `Producto` entity with its attributes and annotations.

2.  **Part 2: Persistence and Business Logic**
    *   Create the `ProductoRepository` interface.
    *   Implement the `ProductoService` with the required business methods.

3.  **Part 3: Data Transfer Objects (DTOs)**
    *   Create `ProductoDTO`, `ProductoResponseDTO`, and `ActualizarStockDTO`.
    *   Implement validation annotations for the DTOs.

4.  **Part 4: REST Controller Layer**
    *   Develop the `ProductoController` to expose the API endpoints.
    *   Map the HTTP methods (GET, POST, PUT, PATCH, DELETE) to the corresponding service methods.

5.  **Part 5: Global Exception Handling**
    *   Create custom exceptions, `ProductoNotFoundException` and `StockInsuficienteException`.
    *   Implement a `GlobalExceptionHandler` to manage API error responses.

6.  **Part 6: API Documentation**
    *   Add the Springdoc OpenAPI dependency to the `pom.xml` file.
    *   Annotate the controller and DTOs to generate comprehensive Swagger documentation.

7.  **Part 7: Final Testing and Validation**
    *   Thoroughly test all API endpoints using the Swagger UI.
    *   Verify that all requirements from the PDF are met.
