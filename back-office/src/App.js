import logo from './logo.svg';
import './App.css';
import React, { Component } from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import axios from 'axios';
import {useState, useEffect} from 'react';



class Home extends Component{
  
  constructor(props){
    super(props);
    this.url = "http://localhost:8080";
    
  this.state = {
    products : [],
    selectedProduct : null

  };
this.handleButtonClick = this.handleButtonClick.bind(this);
this.handleDelete = this.handleDelete.bind(this);
this.setButtons = this.setButtons.bind(this);
}


componentDidMount(){
this.setButtons();
}

setButtons(){
  axios.get(this.url +`/list`)
  .then(res => {
    const prods = res.data;
    this.setState({products:prods });
    console.log(this.state.products)
  });
}




handleDelete(event){
  axios.delete(`http://localhost:8080/` + this.state.product.id)
  .then(() => {
    this.setButtons();
  });
}
handleButtonClick(v){
  this.setState({product:v});
}


render(){
  return(
    <div>
      {
      this.state.products.map(v => {
      return (<div><button onClick={() => this.handleButtonClick(v)}>{v.name}</button> <br/> </div>)
      })
    }
    <div>
    <button onClick={this.handleEdit}>Edit</button>
    <button onClick={this.handleDelete}>Delete</button>
    </div>
      </div>
  );
}
}


class Adder extends Component{
  constructor(props){
    super(props);
    this.url = "http://localhost:8080/add/";
    this.state = {
      name:'',
      ingredients:'',
      price:0
    };
    this.handleChange = this.handleChange.bind(this);
    this.handlePost = this.handlePost.bind(this);
}

handlePost(){
  const product = {
    name:this.state.name,
    ingredients:this.state.ingredients,
    price:this.state.price
  };
  axios.post(this.url,product);

}
handleChange(event) {
  const target = event.target;
  const name = target.name;
  const value = target.value;
  
  this.setState({
    [name]: value
  });

}
render(){
  return (
    <div>
    <h2>Add Product</h2>
    Name:
    <br/>
    <input type="text" name="name" value={this.state.name} onChange={this.handleChange} />
    <br/>
    Ingredients:
    <br/>
    <input type="text" name="ingredients" value={this.state.ingredients} onChange={this.handleChange}/>
    <br/>
    Price:
    <br/>
    <input type="text" name="price" value={this.state.price} onChange={this.handleChange} />
    <br/>
    <button onClick={this.handlePost}>Add</button>
  </div>
  );
}
}




function App() {
  


  
  return (
    <div>
    <Router>
    <div>
      <nav>
        <ul>
          <li>
            <Link to="/">List</Link>
          </li>
          <li>
            <Link to="/add">Add</Link>
          </li>
          <li>
            <Link to="/edit">Edit</Link>
          </li>
        </ul>
      </nav>

      <Switch>
        <Route path="/add">
          <Adder />
        </Route>
        <Route path="/edit">
          <Edit />
        </Route>
        <Route path="/">
          <Home />
        </Route>
      </Switch>
    </div>
  </Router>

  </div>
  );
}

function List() {
  return <h2>List</h2>;

}

function Add() {

}

function Edit() {
  return <h2>Edit</h2>;
}

export default App;
