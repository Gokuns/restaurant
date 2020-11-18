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
}

componentDidMount(){
  axios.get(this.url +`/list`)
  .then(res => {
    const prods = res.data;
    this.setState({products:prods });
    console.log(this.state.products)
  });
}

render(){
  return(
    <div>
      {
      this.state.products.map(v => {
      return (<div><button >{v.name}</button> <br/> </div>)
      })
    }
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
