const sql = require("./db.js");

// constructor
const Subcategory = function(subcategory) {
  this.Name = subcategory.Name;
  this.IconUrl = subcategory.IconUrl;
  this.CategoryName = subcategory.CategoryName;
};

Subcategory.create = (newSubcategory, result) => {
  sql.query("INSERT INTO subcategory SET ?", newSubcategory, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    console.log("created category: ", { name: res.insertName, ...newSubcategory });
    result(null, { name: res.insertName, ...newSubcategory });
  });
};

Subcategory.findByName = (subcategoryName, result) => {
  sql.query(`SELECT * FROM subcategory WHERE name = ${subcategoryName}`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found subcategory: ", res[0]);
      result(null, res[0]);
      return;
    }

    // not found Subcategory with the name
    result({ kind: "not_found" }, null);
  });
};

Subcategory.getAll = result => {
  sql.query("SELECT * FROM subcategory", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("subcategorys: ", res);
    result(null, res);
  });
};

Subcategory.updateByName = (name, subcategory, result) => {
  sql.query(
    "UPDATE category SET Name = ?, IconUrl = ?, CategoryName = ? WHERE name = ?",
    [subcategory.Name, subcategory.IconUrl, subcategory.CategoryName, name],
    (err, res) => {
      if (err) {
        console.log("error: ", err);
        result(null, err);
        return;
      }

      if (res.affectedRows == 0) {
        // not found Subcategory with the name
        result({ kind: "not_found" }, null);
        return;
      }

      console.log("updated subcategory: ", { name: name, ...subcategory });
      result(null, { name: name, ...subcategory });
    }
  );
};

Subcategory.remove = (name, result) => {
  sql.query("DELETE FROM subcategory WHERE name = ?", name, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    if (res.affectedRows == 0) {
      // not found Subcategory with the name
      result({ kind: "not_found" }, null);
      return;
    }

    console.log("deleted subcategory with name: ", name);
    result(null, res);
  });
};

Subcategory.removeAll = result => {
  sql.query("DELETE FROM subcategory", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log(`deleted ${res.affectedRows} subcategorys`);
    result(null, res);
  });
};

module.exports = Subcategory;