const bodyParser = require('body-parser');
const express = require('express');
const { registerdetails, trackStatus } = require('./db/user');
const statusSchema = require('./db/statusSchema');
const app = express()
const PORT = process.env.PORT || 3008;

//Middlewares
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({extended: true}))


// TODO ENDPOINTS
app.post('/Register', async(req, res) => {
    
    try{
        const  {holdername,vehiclenumber,chassisnumber,insurancenumber,licensenumber}= req.body;
        const vehicle = new registerdetails( {holdername,vehiclenumber,chassisnumber,insurancenumber,licensenumber});
        await vehicle.save();
        res.status(200).send('Vehicle registered successfully');
    }catch(err){
        console.error(err)
        res.status(404).send('Vehicle already registered')
    }
    
});

app.get('/Registers/:vehicalnumber', async(req, res) => {
    
    try{
        const vehicle = await registerdetails.find( {});
        if (!vehicle){
            res.status(404).send('Vehical not found');
        }else{
            res.status(200).json(vehicle);
        }
    }catch(err){
        console.error(err)
        res.status(500).send('Error getting vehical details')
    }
    
});


app.post('/status',async (req, res) => {
    try{
        const  {vehiclenumber,engine,brake,healthstatus}= req.body;
        const status = new trackStatus({vehiclenumber,engine,brake,healthstatus});
        await status.save();
        res.status(201).send('Vehical status saved sucessfully');
    }catch(err){
        console.error(err);
        res.status(500).send('Error saving vehical status')
    }
        
});

app.get('/statuss/:vehicalnumber',async (req, res) => {
    try{
        
        const status=await trackStatus.find({});
        if(!status){
            res.status(404).send('Status not found');
        }else{
            res.status(200).json(status)
        }
    }catch(err){
        console.error(err);
        res.status(500).send ('Error getting vehical status');
    }
});

app.listen(PORT, () => console.log(`Application listening on port ${PORT}!`))