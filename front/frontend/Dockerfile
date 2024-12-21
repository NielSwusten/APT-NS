# Step 1: Build the React app
FROM node:20 AS build

# Set the working directory
WORKDIR /app

# Ensure correct permissions for node_modules (helps avoid permission issues)
RUN mkdir -p /app/node_modules && chmod 777 /app/node_modules

# Copy package.json and package-lock.json (or yarn.lock)
COPY package*.json ./

# Install dependencies
RUN npm install --unsafe-perm=true

# Copy the rest of the application code
COPY . .

# Build the React application
RUN npm run build

# Step 2: Serve the React app with a lightweight web server (Nginx)
FROM nginx:alpine

# Copy the build output from the previous build stage
COPY --from=build /app/build /usr/share/nginx/html

# Expose the port the app will run on
EXPOSE 80

# Command to run Nginx server
CMD ["nginx", "-g", "daemon off;"]
