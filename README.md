# spring-food-ordering

This application is a simple food ordering application

## Installation

Create a database **food** <br/>
`CREATE DATABASE food;`

Download and import the [SQL file](https://github.com/Pranesh-vimal/spring-food-ordering/blob/master/food.sql) on your database.

Add paypal properties in application.properties <br/>
`paypal.client.id=` <br/>
`paypal.client.secret=` <br/>
`paypal.mode=` <br/>

## Usage

This application has user side and admin side. On user side we can order the food and on admin side the order will be processed.

Url for admin login <br/>
`/admin/login`

Admin login credentials <br/>
username : **admin123** <br/>
password : **admin123** <br/>

Staff login credentials <br/>
username : **staff123** <br/>
password : **staff123** <br/>

Url for products management <br/>
`/admin/products`

Url for orders management <br/>
`/admin/orders`

Url for users management <br/>
`/admin/users`

## Contributors

<a href="https://github.com/Pranesh-vimal/spring-food-ordering/graphs/contributors" target="_blank">
  <img src="https://contrib.rocks/image?repo=Pranesh-vimal/spring-food-ordering" />
</a>
