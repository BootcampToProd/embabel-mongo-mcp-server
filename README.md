# 🍃 Embabel Framework: MongoDB MCP Server

This repository demonstrates how to build a **MongoDB MCP Server** using the **Embabel Framework** and Spring Boot. The application acts as a bridge between AI agents (like Claude Desktop) and your **MongoDB Database**, allowing the AI to list databases, run complex queries, and insert documents through natural language.

> **⚠️ Note:** This is **not** an official MongoDB MCP server. It is a **demo** MCP server created specifically to demonstrate how developers can build their own MCP servers that interact with databases using the Embabel Framework.

📖 **Complete Guide**: For detailed explanations and a full code walkthrough, read our comprehensive tutorial.<br>
👉 [**Build MongoDB MCP Server Using Embabel Framework**](https://bootcamptoprod.com/embabel-mongodb-mcp-server/)

🎥 **Video Tutorial**: Prefer hands-on learning? Watch our step-by-step implementation guide.<br>
👉 YouTube Tutorial - [**Build a MongoDB MCP Server Using Embabel Framework | Chat With Your Database using AI**](https://youtu.be/h6aadfCDwOc)

<p align="center">
  <a href="https://youtu.be/h6aadfCDwOc">
    <img src="https://img.youtube.com/vi/h6aadfCDwOc/0.jpg" alt="Build a MongoDB MCP Server Using Embabel Framework | Chat With Your Database using AI" />
  </a>
</p>

<p align="center">
  ▶️ <a href="https://youtu.be/h6aadfCDwOc">Watch on YouTube</a>
</p>

---

## ✨ What This Project Demonstrates

This application showcases how to **connect AI agents to a Database** using the MCP Server:

- **Model Context Protocol (MCP)** implementation using Embabel framework.
- **Database Integration** connecting an AI agent to MongoDB.
- **Tool Exposure** using Embabel's `@Export` annotation to turn Java service methods into AI tools.
- **Data Operations** including listing databases/collections, executing simple & complex JSON queries, managing indexes, and inserting documents.

---

## 📋 Prerequisites

Before running this application, ensure you have:

- **Java 21** or higher
- **OpenRouter API Key** (free tier available at [OpenRouter.ai](https://openrouter.ai/))
- **MongoDB Instance** (Running locally on port 27017 or via MongoDB Atlas)
- **Node.js** (Optional, required if testing with MCP Inspector)
- **Claude Desktop App** (Optional, for real-world agent testing)

---

## 🚀 Quick Start

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/BootcampToProd/embabel-mongo-mcp-server.git
cd embabel-mongo-mcp-server
```

### 2️⃣ Configure Environment Variables
Provide your OpenRouter API key and MongoDB connection details.
```bash
OPENAI_API_KEY={YOUR_OPENROUTER_API_KEY}
MONGO_HOST=localhost
MONGO_PORT=27017
```

### 3️⃣ Build the Project
```bash
mvn clean install
```

### 4️⃣ Run the Application
```bash
mvn spring-boot:run
```
The server will start on `http://localhost:8080`. The MCP endpoint is exposed at `/sse`.

---

## 💡 How to Test

You can test the server using **Claude Desktop** or the **MCP Inspector**.

### 🤖 Option 1: Claude Desktop (Recommended)

1. Open your Claude Desktop configuration file:
    - **Mac:** `~/Library/Application Support/Claude/claude_desktop_config.json`
    - **Windows:** `%APPDATA%\Claude\claude_desktop_config.json`

2. Add the following configuration:
```json
{
  "mcpServers": {
    "embabel-mongo-server": {
      "command": "npx",
      "args": [
        "-y",
        "mcp-remote",
        "http://localhost:8080/sse"
      ]
    }
  }
}
```

3. Restart Claude Desktop. You should see your mcp server and available tools.

4. **Ask Claude:**
    - "List all databases."
    - "Create a collection named 'users' in 'test_db'."
    - "Insert a user named Alice with age 28 into 'test_db.users'."
    - "Find all users in 'test_db.users' where age is greater than 25."

### 🔍 Option 2: MCP Inspector

If you want to debug the tools manually:

1. Ensure the Spring Boot app is running.
2. Run the inspector in your terminal:
   ```bash
   npx @modelcontextprotocol/inspector
   ```
3. In the browser window that opens:
    - Select **SSE**.
    - Enter URL: `http://localhost:8080/sse`.
    - Click **Connect** and test the tools via the UI.