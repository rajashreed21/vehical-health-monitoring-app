const mongoose = require('mongoose');

const statusSchema = new mongoose.Schema({
    
    engine: {
        type: String,
    },
    brake: {
        type: String,
    },
    healthstatus:{
        type:String,
    }
});

module.exports = statusSchema;