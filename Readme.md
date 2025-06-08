# Weather Web Application 🌤️

A basic Spring Boot backend weather application built for practice and learning purposes. This application provides real-time weather information through a RESTful API.

##  Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

##  Features

- **Real-time Weather Data**: Fetch current weather information for any city
- **RESTful API**: Clean and intuitive API endpoints
- **Spring Boot Backend**: Robust and scalable backend architecture
- **External API Integration**: Integration with weather service providers
- **Error Handling**: Comprehensive error handling and validation
- **JSON Response Format**: Structured JSON responses for easy consumption

##  Technologies Used

- **Java** - Programming language
- **Spring Boot** - Application framework
- **Spring Web** - For building REST APIs
- **Maven** - Dependency management
- **External Weather API** - For fetching weather data (OpenWeatherMap, WeatherAPI, etc.)
- **Jackson** - JSON processing

##  Prerequisites

Before running this application, make sure you have the following installed:

- Java 8 or higher
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, or VS Code)
- Weather API key (from your chosen weather service provider)

##  Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Aravind556/Weather_webapp.git
   cd Weather_webapp
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Configure application properties**
   - Copy `application.properties.example` to `application.properties`
   - Add your weather API key and configuration

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

##  Configuration

Create an `application.properties` file in `src/main/resources/` with the following configuration:

```properties
# Server configuration
server.port=8080

# Weather API configuration
weather.api.key=YOUR_API_KEY_HERE
weather.api.base-url=https://api.openweathermap.org/data/2.5

# Logging configuration
logging.level.com.example.weather=DEBUG
```

##  API Endpoints

### Get Current Weather
```http
GET /api/weather/current?city={cityName}
```

**Parameters:**
- `city` (required): Name of the city

**Response:**
```json
{
  "city": "London",
  "country": "GB",
  "temperature": 15.5,
  "description": "Partly cloudy",
  "humidity": 65,
  "windSpeed": 3.2,
  "timestamp": "2024-01-15T10:30:00Z"
}
```

### Get Weather Forecast
```http
GET /api/weather/forecast?city={cityName}&days={numberOfDays}
```

**Parameters:**
- `city` (required): Name of the city
- `days` (optional): Number of forecast days (default: 5)

##  Usage

### Using cURL

```bash
# Get current weather for London
curl "http://localhost:8080/api/weather/current?city=London"

# Get 3-day forecast for New York
curl "http://localhost:8080/api/weather/forecast?city=New York&days=3"
```

### Using a REST Client

You can also test the API using tools like:
- Postman
- Insomnia
- Thunder Client (VS Code extension)

##  Project Structure

```
Weather_webapp/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── weather/
│       │               ├── WeatherApplication.java
│       │               ├── controller/
│       │               │   └── WeatherController.java
│       │               ├── service/
│       │               │   └── WeatherService.java
│       │               ├── model/
│       │               │   └── WeatherResponse.java
│       │               └── config/
│       │                   └── WebConfig.java
│       └── resources/
│           ├── application.properties
│           └── static/
├── pom.xml
└── README.md
```


## 🐛 Troubleshooting

### Common Issues

1. **API Key Issues**
   - Ensure your weather API key is valid and properly configured
   - Check if you've exceeded your API rate limits

2. **Port Conflicts**
   - If port 8080 is busy, change the port in `application.properties`:
     ```properties
     server.port=8081
     ```

3. **Network Issues**
   - Verify your internet connection
   - Check if external weather API is accessible

##  Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open an issue first to discuss what you would like to change.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


## 🙏 Acknowledgments

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [OpenWeatherMap API](https://openweathermap.org/api) (if used)
- Weather service providers for their APIs

---

**Note**: This is a practice project created for learning Spring Boot development. Feel free to use it as a reference for your own projects!