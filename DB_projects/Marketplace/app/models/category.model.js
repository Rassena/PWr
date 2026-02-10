const sql = require("./db.js");

// constructor
const Category = function(category) {
  this.Name = category.Name;
  this.IconUrl = category.IconUrl;
  this.Color = category.Color;

};

Category.create = (newCategory, result) => {
  sql.query("INSERT INTO category SET ?", newCategory, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    console.log("created category: ", { name: res.insertName, ...newCategory });
    result(null, { name: res.insertName, ...newCategory });
  });
};

Category.findByName = (categoryName, result) => {
  sql.query(`SELECT * FROM category WHERE name = "${categoryName}"`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found category: ", res[0]);
      result(null, res[0]);
      return;
    }

    // not found Category with the name
    result({ kind: "not_found" }, null);
  });
};

Category.findByColor = (categoryColor, result) => {
  sql.query(`SELECT * FROM category WHERE Color = "${categoryColor}"`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }
      console.log("found category: ", res);
      result(null, res);
      return;

    // not found Category with the name
    result({ kind: "not_found" }, null);
  });
};

Category.getAll = result => {
  sql.query("SELECT * FROM category", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("categorys: ", res);
    result(null, res);
  });
};

Category.getPage = (page,inPage,result) => {
  onPage = parseInt(inPage);
  sql.query(
    "SELECT * FROM category LIMIT ? OFFSET ?",
    [onPage, onPage*(parseInt(page)-1)],
   (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("categorys: ", res);
    result(null, res);
  });
};

Category.updateByName = (name, category, result) => {
  sql.query(
    "UPDATE category SET Name = ?, IconUrl = ?, Color = ? WHERE name = ?",
    [category.Name, category.IconUrl, category.Color, name],
    (err, res) => {
      if (err) {
        console.log("error: ", err);
        result(null, err);
        return;
      }

      if (res.affectedRows == 0) {
        // not found Category with the name
        result({ kind: "not_found" }, null);
        return;
      }

      console.log("updated category: ", { name: name, ...category });
      result(null, { name: name, ...category });
    }
  );
};

Category.remove = (name, result) => {
  sql.query("DELETE FROM category WHERE name = ?", name, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    if (res.affectedRows == 0) {
      // not found Category with the name
      result({ kind: "not_found" }, null);
      return;
    }

    console.log("deleted category with name: ", name);
    result(null, res);
  });
};

Category.removeAll = result => {
  sql.query("DELETE FROM category", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log(`deleted ${res.affectedRows} categorys`);
    result(null, res);
  });
};

module.exports = Category;