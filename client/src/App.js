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
import 'bootstrap/dist/css/bootstrap.min.css'




class Home extends Component{
  
  constructor(props){
    super(props);
    this.url = "http://localhost:8080";
    
  this.state = {
    products : [],
    selectedProduct : null,
    categories: [],
    cart : [],
    totalPrice:0,
    totalNum:0

  };
  this.categoryClick = this.categoryClick.bind(this);
  this.refreshLst = this.refreshLst.bind(this);
  this.handleAddToCart = this.handleAddToCart.bind(this);
  this.handleCheck = this.handleCheck.bind(this);
  this.handleRemove = this.handleRemove.bind(this);
}

componentDidMount(){
  axios.get(this.url +`/list`)
  .then(res => {
    const prods = res.data;
    this.setState({products:prods });
    console.log(this.state.products)
  });
  axios.get(this.url + '/categories')
  .then(res => {  
    const cats = res.data;
    this.setState({categories:cats });
    console.log(this.state.categories)
  });

}

categoryClick(c){
  console.log(c)
  axios.get(this.url+'/view/'+c)
  .then(res => {  
    const prods = res.data;
    this.setState({products:prods });
    console.log(this.state.products)
  });
}

refreshLst(){
  axios.get(this.url +`/list`)
  .then(res => {
    const prods = res.data;
    this.setState({products:prods });
    console.log(this.state.products)
  });
  axios.get(this.url + '/categories')
  .then(res => {  
    const cats = res.data;
    this.setState({categories:cats });
    console.log(this.state.categories)
  });
}

handleAddToCart(v){
  console.log(v.data)
  const elem = {
    data:v,
    num:1
  };
  if(this.handleCheck(elem)){
    let elems = [...this.state.cart];  
    let index = elems.findIndex(el => el.data==elem.data);
    const newElem = {
      data:elem.data,
      num:elems[index].num+1
    }
    elems[index] = newElem;                  
    this.setState({ cart:elems,
              totalNum:this.state.totalNum+1,
      totalPrice:this.state.totalPrice+v.price });
  }else{
  this.setState({cart:this.state.cart.concat(elem),
    totalNum:this.state.totalNum+1,
  totalPrice:this.state.totalPrice+v.price});
  }
}

handleAddPlus (elem){

  if(this.handleCheck(elem)){
    let elems = [...this.state.cart];  
    let index = elems.findIndex(el => el.data==elem.data);
    const newElem = {
      data:elem.data,
      num:elems[index].num+1
    }
    elems[index] = newElem;                  
    this.setState({ cart:elems, 
      totalNum:this.state.totalNum+1,
      totalPrice:this.state.totalPrice+elem.data.price });
  }else{
  this.setState({cart:this.state.cart.concat(elem),
  totalNum:this.state.totalNum+1,
  totalPrice:this.state.totalPrice+elem.data.price});
  }
  
}

handleCheck(val) {
  return this.state.cart.some(item => val.data === item.data);
}

handleRemove(v){
  if(this.state.cart.length===1 && v.num==1){
    this.setState({cart:[],totalNum:0,totalPrice:0});
  }
  else if(v.num==1){
    const newList = this.state.cart.filter((item) => item.data !== v.data);
    this.setState({cart:newList,   totalNum:this.state.totalNum-1,
      totalPrice:this.state.totalPrice-v.data.price});
  }else{
    let elems = [...this.state.cart];  
    let index = elems.findIndex(el => el.data==v.data);
    const newElem = {
      data:v.data,
      num:elems[index].num-1
    };
    elems[index] = newElem;                  
    this.setState({ cart:elems,
      totalNum:this.state.totalNum-1,
      totalPrice:this.state.totalPrice-v.data.price});
  }

}

render(){
  return(
    <div class="container" className="Cont">
      <div class= "row">
      <div class= "col-sm" className="catBtn">
        <div>
          <button class="btn btn-primary btn"onClick={this.refreshLst}>All</button>
          <br/>
          {this.state.categories.map(c =>{
           return (<div>
              <button class="btn btn-primary btn"onClick={() => this.categoryClick(c)}>{c}</button>
              <br/>
              </div>
               );
               }
               )}
          </div>
        </div>
        
<div class= "col-sm" className="clList">
<div  class="row row-cols-1 row-cols-md-2">
{ this.state.products.map(v => 
  { return (
    <div class="card">
  <div class="card-body">
    <h5 class="card-title">{v.name}</h5>
    <p class="card-text">{v.category}</p>
    <p class="card-text">{v.details}</p>   
    <h4>{v.price} TL</h4>
    <button onClick={() => this.handleAddToCart(v)} class="btn btn-primary">Add to Cart</button>
  </div>
    </div>
  ) 
}) 
}
</div>
</div>
<div  class="col-sm">
  <div className="Cart">
  <div class = "row">

  <table class="table table-hover table-sm">
  <thead class="thead-light">
  <tr>
  <th scope="col">Add</th> 
      <th scope="col">Name</th>
      <th scope="col">Count</th>
      <th scope="col">Price</th>
      <th scope="col">Remove</th>
    </tr>
  </thead>
  <tbody>
    
  {this.state.cart.map(c =>{
           return (
              <tr>
              <button class="btn btn-outline-success btn-sm" onClick={() => this.handleAddPlus(c)}>+</button>
              <td>{c.data.name}</td>
              <td>{c.num}</td>
              <td>{c.data.price*c.num}</td>
              <button class="btn btn-outline-danger btn-sm" onClick={() => this.handleRemove(c)}>-</button>
              </tr>
               );
               }
               )}
    </tbody>
  </table>
  </div>
  <div class="w-100"></div>
  <div class='row'>
    <div className = "TotalTable">
    <table class="table table-sm">
      <thead class="thead-light">
      <tr>
      <th scope="col">Total Items</th> 
      <th scope="col">Total Count</th>
      </tr>
      </thead>
    <tbody>
      <tr>
      <td>{this.state.totalNum}</td>
      <td>{this.state.totalPrice} TL</td>
      </tr>
    </tbody>
    </table> 
    </div>     
  </div>
  <div class='row'>
    <div className = "TotalTable">
    <button class="btn btn-success" > Checkout </button>
    </div>
  </div>
  </div>
</div>
</div>
</div>
  );
}
}


function App() {
  return (
    <div className="App">
      <Home/>
    </div>
  );
}

export default App;
