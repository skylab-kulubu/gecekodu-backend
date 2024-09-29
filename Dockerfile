FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
WORKDIR /app

EXPOSE 8080

COPY BusinessLayer/BusinessLayer.csproj ./BusinessLayer/
COPY CoreLayer/CoreLayer.csproj ./CoreLayer/
COPY DataAccessLayer/DataAccessLayer.csproj ./DataAccessLayer/
COPY EntityLayer/EntityLayer.csproj ./EntityLayer/
COPY WebApiLayer/WebApiLayer.csproj ./WebApiLayer/
RUN dotnet restore ./WebApiLayer/WebApiLayer.csproj

COPY . ./
WORKDIR /app/WebApiLayer
RUN dotnet publish -c Release -o out

FROM mcr.microsoft.com/dotnet/aspnet:8.0 AS runtime
WORKDIR /app
COPY --from=build /app/WebApiLayer/out .

# Install netcat-openbsd in the runtime image
RUN apt-get update && apt-get install -y netcat-openbsd && rm -rf /var/lib/apt/lists/*

# Copy the wait-for.sh script
COPY wait-for.sh /app/wait-for.sh
RUN chmod +x /app/wait-for.sh

ENTRYPOINT ["./wait-for.sh", "postgres:5432", "--", "dotnet", "WebApiLayer.dll", "--environment=Development"]
