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
ENTRYPOINT ["dotnet", "WebApiLayer.dll", "--environment=Development"]