# Step 1: Use the full-weight Node.js 16 image as the base image
FROM node:16

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the package.json and package-lock.json files
COPY package*.json ./

# Step 4: Install dependencies
RUN npm install

# Step 5: Copy the rest of the application files
COPY . .

# Step 6: Build the application with Webpack
RUN npm run build

# Step 7: Expose port 8080
EXPOSE 8080

# Step 8: Start the application
CMD ["npm", "start"]
