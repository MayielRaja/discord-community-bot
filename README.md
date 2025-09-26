# NexusBot - A Multi-API Discord Utility Bot

A **Java**-based Discord bot that provides real-time information and utilities to server members by integrating multiple external REST APIs. This project was developed as a personal initiative to sharpen backend development skills, focusing on API integration, JSON parsing, and professional coding practices.

---
## ✨ Features

* **/define <word>**: Looks up the definition of any English word using the DictionaryAPI and displays the results in a clean, embedded format.
* **/weather <city>**: Fetches the current weather for any city using WeatherAPI.com and presents the forecast in a neat reply.
* **/say <message>**: A fun utility command that makes the bot repeat a user's message.
* **/ping** A simple latency check command to ensure the bot is online and responsive.

---
## 🛠️ Tech Stack

* **Core Language:** **Java**
* **Discord API Wrapper:** **JDA (Java Discord API)**
* **Dependency Management:** **Maven**
* **HTTP Client:** **OkHttp** for making requests to external APIs.
* **JSON Parsing:** **org.json**
* **Version Control:** **Git & GitHub**
* **APIs Used:** [WeatherAPI.com](https://www.weatherapi.com/), [DictionaryAPI](https://dictionaryapi.dev/)

---
## 🚀 Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

* Java Development Kit (JDK) 11 or higher.
* [Apache Maven](https://maven.apache.org/download.cgi) installed.

### Installation

1.  **Clone the repository:**
    ```sh
    git clone [https://github.com/MayielRaja/discord-community-bot.git](https://github.com/MayielRaja/discord-community-bot.git)
    ```
2.  **Navigate to the project directory:**
    ```sh
    cd discord-community-bot
    ```
3.  **Import the project into your IDE** (like Eclipse or IntelliJ IDEA) as an existing Maven project. Your IDE will automatically download all the necessary dependencies.

---
## ⚙️ Configuration

This project uses a `config.properties` file to securely manage secret keys.

1.  Navigate to the `src/main/resources` directory.
2.  Create a new file named `config.properties`.
3.  Add your secret keys to this file (see the template in the repository).
4.  **Important:** This file is listed in `.gitignore` and should never be committed to GitHub.

---
## 📜 License

Distributed under the MIT License. See `LICENSE` for more information.
