var crypto = require('crypto');
var uuid = require('uuid');
var express = require('express');
var mysql = require('mysql');
var bodyParser = require('body-parser');

//connect to mysql
var con = mysql.createConnection({
   host:'localhost',
   user:'root',
   password:'1410',
   database:'project'
});
con.connect(function(err) {
    if (err) throw err;
    console.log("Connected!");
  });
//password util
var genRandomString=function(length){
 return crypto.randomBytes(Math.ceil(length/2))
    .toString('hex')
    .slice(0,length);
};

var sha512 = function(password,salt){
     var hash = crypto.createHmac('sha512',salt);
     hash.update(password);
     var value = hash.digest('hex');
     return{
         salt:salt,
         passwordHash:value  
     };
};

function saltHashPassword(userPassword){
    var salt = genRandomString(16);
    var passwordData = sha512(userPassword,salt);
    return passwordData;
}

function checkHashPassword(userPassword,salt){
    var passwordData = sha512(userPassword,salt);
    return passwordData;
}


var app=express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended : true}));

app.post('/cregister/',(req,res,next)=>{
  var post_data = req.body;
  console.log('Requested!!');
  var uid = Math.floor(Math.random()*900000) + 100000;
  var plaint_password = post_data.password;
  var hash_data = saltHashPassword(plaint_password);
  var password = hash_data.passwordHash;
  var salt = hash_data.salt;
  var newname;
  var name = post_data.name;
  var email = post_data.email;
  var phone = post_data.phone;
  var street = post_data.street;
  var city = post_data.city;
  var zipcode = post_data.zipcode;

  con.query('SELECT * FROM customer where email=?',[email],function(err,result,fields){
     con.on('error',function(err){
         console.log('[MySQL Error]',err);
     });

    if(result && result.length)
        res.json('User already exists!!!');
    else
    {
     con.query('INSERT INTO customer (customer_id,cust_name,cust_email,encrypted_password,salt,cust_phone) VALUES (?,?,?,?,?,?)',[uid,name,email,password,salt,phone],function(err,result,fields){
        if(err){
            throw err
        }
      })
      con.query('INSERT INTO custaddress (customer_id,street,city,zipcode) VALUES (?,?,?,?)',[uid,street,city,zipcode],function(err,result,fields){
        if(err){
            throw err
        }
        res.json('Register successful');
      })
   }
  });

})


app.post('/clogin/',(req,res,next)=>{
 
    var post_data = req.body;
    console.log('Requested');
    //extract email and password from request
    var user_password = post_data.password;
    var email = post_data.email;

    con.query('SELECT * FROM customer where cust_email=?',[email],function(err,result,fields){
        if(err){
            throw err
        }
   
       if(result && result.length)
       {
        var salt = result[0].salt;
        var encrypted_password = result[0].encrypted_password;
        var hashed_password = checkHashPassword(user_password,salt).passwordHash;
        if(encrypted_password == hashed_password)
        {
           res.end(JSON.stringify(result[0]))
        }
        else
        {
            res.end(JSON.stringify('Wrong password'));
        }
       }
        
       else
       {
        res.json("User not exists!!");
      }
     });

})
  
app.post('/sregister/',(req,res,next)=>{
    var post_data = req.body;
    var uid = Math.floor(Math.random()*900000) + 100000;
    var plaint_password = post_data.password;
    var hash_data = saltHashPassword(plaint_password);
    var password = hash_data.passwordHash;
    var salt = hash_data.salt;
  
    var name = post_data.name;
    var email = post_data.email;
    var phone = post_data.phone;
    var bank_account = post_data.bankacc;
    var street = post_data.street;
    var city = post_data.city;
    var zipcode = post_data.zipcode;
  
    con.query('SELECT * FROM seller where email=?',[email],function(err,result,fields){
       con.on('error',function(err){
           console.log('[MySQL Error]',err);
       });
  
      if(result && result.length)
          res.json('User already exists!!!');
      else
      {
       con.query('INSERT INTO seller (seller_id,sel_name,sel_email,sel_enc_password,sel_salt,sel_phone,bank_account) VALUES (?,?,?,?,?,?,?)',[uid,name,email,password,salt,phone,bank_account],function(err,result,fields){
          if(err){
              throw err
          }
        })
        con.query('INSERT INTO selleraddress (seller_id,sel_street,sel_city,sel_zipcode) VALUES (?,?,?,?)',[uid,street,city,zipcode],function(err,result,fields){
          if(err){
              throw err
          }
          res.json('Register successful');
        })
     }
    });
  
  })


  app.post('/slogin/',(req,res,next)=>{
 
    var post_data = req.body;
    console.log('Requested');
    //extract email and password from request
    var user_password = post_data.password;
    var email = post_data.email;

    con.query('SELECT * FROM seller where sel_email=?',[email],function(err,result,fields){
        if(err){
            throw err
        }
   
       if(result && result.length)
       {
        var sel_salt = result[0].sel_salt;
        var sel_enc_password = result[0].sel_enc_password;
        var hashed_password = checkHashPassword(user_password,sel_salt).passwordHash;
        if(sel_enc_password == hashed_password)
        {
           res.end(JSON.stringify(result[0]))
        }
        else
        {
            res.end(JSON.stringify('Wrong password'));
        }
       }
        
       else
       {
        res.json("User not exists!!");
      }
     });

})




app.post('/electronics/',(req,res,next)=>{
 
    var post_data = req.body;
    console.log('Requested');
    //extract email and password from request
    var val = post_data.val;
    

    con.query('SELECT * FROM electronics',function(err,result,fields){
        if(err){
            throw err
        }

        res.send(result);
     });

})
  



app.post('/pantry/',(req,res,next)=>{
 
    var post_data = req.body;
    console.log('Requested');
    //extract email and password from request
    var val = post_data.val;
    

    con.query('SELECT * FROM pantry',function(err,result,fields){
        if(err){
            throw err
        }

        res.send(result);
     });

})
  

app.post('/men/',(req,res,next)=>{
 
    var post_data = req.body;
    console.log('Requested');
    //extract email and password from request
    var val = post_data.val;
    

    con.query('SELECT * FROM clothes where productId between 3456101 and 3456125',function(err,result,fields){
        if(err){
            throw err
        }

        res.send(result);
     });

})  

app.post('/women/',(req,res,next)=>{
 
    var post_data = req.body;
    console.log('Requested');
    //extract email and password from request
    var val = post_data.val;
    

    con.query('SELECT * FROM clothes where productId between 1567101 and 1567110',function(err,result,fields){
        if(err){
            throw err
        }

        res.send(result);
     });

})  

app.post('/kids/',(req,res,next)=>{
 
    var post_data = req.body;
    console.log('Requested');
    //extract email and password from request
    var val = post_data.val;
    

    con.query('SELECT * FROM clothes where productId between 2189101 and 2189110',function(err,result,fields){
        if(err){
            throw err
        }

        res.send(result);
     });

})  

//start server
app.listen(8000,()=>{
    console.log('Restful running on port 8000');
})
