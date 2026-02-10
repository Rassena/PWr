const express = require("express");
const bodyParser = require("body-parser");
const cors = require('cors');

const app = express();

const router = require('./router');


// parse requests of content-type: application/json
app.use(bodyParser.json());

// parse requests of content-type: application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }));


//uploading files front
app.use(express.static('public'));
app.set('view engine', 'ejs');
app.use('/upload', router);

//add cors
app.use(cors());



// simple route
app.get("/", (req, res) => {
  res.json({ message: "Welcome to application." });
});

require("./app/routes/user.routes.js")(app);
require("./app/routes/category.routes.js")(app);
require("./app/routes/subcategory.routes.js")(app);
require("./app/routes/photo.routes.js")(app);
require("./app/routes/purchase.routes.js")(app);
require("./app/routes/ad.routes.js")(app);
require("./app/routes/analytic.routes.js")(app);


// set port, listen for requests
app.listen(3000, () => {
  console.log("Server is running on port 3000.");
});
