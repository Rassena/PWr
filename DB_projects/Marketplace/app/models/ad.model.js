const sql = require("./db.js");

// constructor
const Ad = function(ad) {
  this.Header = ad.Header;
  this.Description = ad.Description;
  this.Price = ad.Price;
  this.Negotiable = ad.Negotiable;
  this.PostDate = ad.PostDate;
  this.DueDate = ad.DueDate;
  this.Id = ad.Id;
  this.SubCategoryName = ad.SubCategoryName;
  this.UserSso = ad.UserSso;
};

Ad.create = (newAd, result) => {

  sql.query("INSERT INTO ad (Header,  Description, Price, Negotiable,  PostDate, DueDate, UserSso, subCategoryName) VALUES (?,?,?,?,NOW(),DATE_ADD(NOW(), INTERVAL 7 DAY),?,?);",
  [newAd.Header, newAd.Description, newAd.Price, newAd.Negotiable, newAd.UserSso, newAd.SubCategoryName], (err, res) => {
  if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    console.log("created ad: ", { id: res.insertId, ...newAd });
    result(null, { id: res.insertId, ...newAd });
  });
};

Ad.findById = (adId, result) => {
  sql.query(`SELECT ad.*, Paid, (if(Now()>DueDate,true,false)) as Expired FROM ad left join purchase on ad.Id=purchase.AdId WHERE ad.Id = ${adId}`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found ad: ", res[0]);
      result(null, res[0]);
      return;
    }

    // not found Ad with the id
    result({ kind: "not_found" }, null);
  });
};

Ad.findAlldWithPhoto = (result) => {
  sql.query(`SELECT ad.*, photo.Url FROM ad ,photo WHERE ad.id = photo.AdId`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found ad: ", res);
      result(null, res);
      return;
    }

    // not found Ad with the id
    result({ kind: "not_found" }, null);
  });
};


Ad.findByPrice = (price1,price2, result) => {
  sql.query(`SELECT ad.*, Paid, (if(Now()>DueDate,true,false)) as Expired FROM ad left join purchase on ad.Id=purchase.AdId  WHERE Price BETWEEN ${price1} AND ${price2};`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length>0) {
      console.log("found ad: ", res);
      result(null, res);
      return;
    }

    // not found Ad with the id
    result({ kind: "not_found" }, null);
  });
};


Ad.findByDates = (postDate1, postDate2, result) => {
  sql.query(
    'select ad.*, Paid, (if(Now()>DueDate,true,false)) as Expired FROM ad left join purchase on ad.Id=purchase.AdId where PostDate BETWEEN ? AND ?;',
    [postDate1, postDate2],
(err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("ads: ", res);
    result(null, res);
  });
};


Ad.getAll = result => {
  

    //sql.query("SELECT * FROM ad", (err, res) => {
    sql.query(" select ad.*, Paid, Date, if(Now()>ad.DueDate, true, false) as Expired from ad left join purchase on ad.Id=purchase.AdId;", (err, res) => {



    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("ads: ", res);
    result(null, res);
  });
};

Ad.getAllActive = result => {
  //sql.query("SELECT * FROM ad", (err, res) => {
  sql.query('select ad.*, Paid, Date, if(Now()>ad.DueDate, true, false) as Expired from ad left join purchase on ad.Id=purchase.AdId where if(Now()<ad.DueDate and Paid is not true, true, false) = true;',
   (err, res) => {
  if (err) {
    console.log("error: ", err);
    result(null, err);
    return;
  }

  console.log("ads: ", res);
  result(null, res);
});
};


Ad.getAllExpired = result => {
  //sql.query("SELECT * FROM ad", (err, res) => {
  sql.query('select ad.*, Paid, Date, if(Now()>ad.DueDate, true, false) as Expired from ad left join purchase on ad.Id=purchase.AdId where if(Now()>ad.DueDate, true, false) = true;',
   (err, res) => {
  if (err) {
    console.log("error: ", err);
    result(null, err);
    return;
  }

  console.log("ads: ", res);
  result(null, res);
});
};

Ad.getAllPaid = result => {
  //sql.query("SELECT * FROM ad", (err, res) => {
  sql.query('select ad.*, Paid, Date, if(Now()>ad.DueDate, true, false) as Expired from ad left join purchase on ad.Id=purchase.AdId where Paid = true;',
   (err, res) => {
  if (err) {
    console.log("error: ", err);
    result(null, err);
    return;
  }

  console.log("ads: ", res);
  result(null, res);
});
};





Ad.getAllActiveByUserSso = (userSso, result) => {
  sql.query('select ad.*, Paid, Date, if(Now()>ad.DueDate, true, false) as Expired from ad left join purchase on ad.Id=purchase.AdId where if(Now()<ad.DueDate and Paid is not true, true, false) = true and ad.UserSso = ?;',
   [userSso],
    (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found ad: ", res);
      result(null, res);
      return;
    }

    // not found Ad with the id
    result({ kind: "not_found" }, null);
  });
};


Ad.getAllPaidByUserSso = (userSso, result) => {
  //sql.query("SELECT * FROM ad", (err, res) => {
  sql.query(
    'select ad.*, Paid, Date, if(Now()>ad.DueDate, true, false) as Expired from ad left join purchase on ad.Id=purchase.AdId where Paid = true and ad.UserSso = ?;',
   [userSso],
  (err, res) => {
  if (err) {
    console.log("error: ", err);
    result(null, err);
    return;
  }

  console.log("ads: ", res);
  result(null, res);
});
};

Ad.getAllExpiredByUserSso = (userSso, result) => {
  //sql.query("SELECT * FROM ad", (err, res) => {
  sql.query(
    'select ad.*, Paid, Date, if(Now()>ad.DueDate, true, false) as Expired from ad left join purchase on ad.Id=purchase.AdId where if(Now()>ad.DueDate, true, false) = true and ad.UserSso = ?;',
   [userSso],
  (err, res) => {
  if (err) {
    console.log("error: ", err);
    result(null, err);
    return;
  }

  console.log("ads: ", res);
  result(null, res);
});
};


Ad.getPage = (page,inPage,result) => {
  onPage = parseInt(inPage);
  sql.query(
    "SELECT ad.*, Paid, (if(Now()>DueDate,true,false)) as Expired FROM ad left join purchase on ad.Id=purchase.AdId  LIMIT ? OFFSET ?",
    [onPage, onPage*(parseInt(page)-1)],
   (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("ads: ", res);
    result(null, res);
  });
};


Ad.updateById = (id, ad, result) => {
  sql.query(
    "UPDATE ad SET Header = ?,  Description = ?, Price = ?, Negotiable = ?,  PostDate = ?, DueDate = ?, UserSso = ?, subCategoryName = ? WHERE id = ?",
    [ad.Header, ad.Description, ad.Price, ad.Negotiable, ad.PostDate, ad.DueDate, ad.UserSso, ad.SubCategoryName, id],
    (err, res) => {
      if (err) {
        console.log("error: ", err);
        result(null, err);
        return;
      }

      if (res.affectedRows == 0) {
        // not found Ad with the id
        result({ kind: "not_found" }, null);
        return;
      }

      console.log("updated ad: ", { id: id, ...ad });
      result(null, { id: id, ...ad });
    }
  );
};

Ad.remove = (id, result) => {
  sql.query("DELETE FROM ad WHERE id = ?", id, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    if (res.affectedRows == 0) {
      // not found Ad with the id
      result({ kind: "not_found" }, null);
      return;
    }

    console.log("deleted ad with id: ", id);
    result(null, res);
  });
};

Ad.removeAll = result => {
  sql.query("DELETE FROM ad", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log(`deleted ${res.affectedRows} ads`);
    result(null, res);
  });
};


Ad.getNegotiable = result => {
  sql.query("SELECT ad.*, Paid, (if(Now()>DueDate,true,false)) as Expired FROM ad left join purchase on ad.Id=purchase.AdId  WHERE ad.Negotiable = 1;", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("ads: ", res);
    result(null, res);
  });
};

Ad.getBySubCategory = (subCategoryName, result) => {
  sql.query(`SELECT ad.*, Paid, (if(Now()>DueDate,true,false)) as Expired FROM ad left join purchase on ad.Id=purchase.AdId  WHERE  ad.SubCategoryName = "${subCategoryName}"`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found ad: ", res);
      result(null, res)
      return;
    }

    // not found Ad with the id
    result({ kind: "not_found" }, null);
  });
};


Ad.findByUserSso = (userSso, result) => {
  sql.query(`SELECT ad.*, Paid, (if(Now()>DueDate,true,false)) as Expired FROM ad left join purchase on ad.Id=purchase.AdId  WHERE  ad.UserSso = "${userSso}"`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found ad: ", res);
      result(null, res)
      return;
    }

    // not found Ad with the id
    result({ kind: "not_found" }, null);
  });
};

module.exports = Ad;