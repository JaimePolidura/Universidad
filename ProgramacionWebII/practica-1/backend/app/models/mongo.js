const mongoose = require('mongoose');

const mongoConnect = () => {
    console.log("Conectando a mongo");
    mongoose.connect("mongodb://localhost/entrega");
};

mongoConnect();

const userSchema = new mongoose.Schema({
    username: String,
    email: String,
    password: String,
    Roles: {type: [String],},
});

const User = mongoose.model('User', userSchema);

module.exports = User;
