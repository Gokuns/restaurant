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
    this.urladd = "http://localhost:8080/add/";


    
  this.state = {
    products : [],
    selectedProduct : null,
    editName:'',
    editDet:'',
    editCat:'',
    editPric:'',
    name:'',
    details:'',
    category:'',
    price:0,
    showEdit:false,
    showAdd:false

  };
this.handleButtonClick = this.handleButtonClick.bind(this);
this.handleDelete = this.handleDelete.bind(this);
this.setButtons = this.setButtons.bind(this);
this.handleEdit = this.handleEdit.bind(this);
this.differentRender = this.differentRender.bind(this);
this.handleChange = this.handleChange.bind(this);
this.editCliked = this.editCliked.bind(this);
this.editCancel = this.editCancel.bind(this);
this.showAdder = this.showAdder.bind(this);
this.handlePost = this.handlePost.bind(this);
this.adderRender = this.adderRender.bind(this);
this.addCancel = this.addCancel.bind(this);
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
handleChange(event) {
  const target = event.target;
  const name = target.name;
  const value = target.value;
  
  this.setState({
    [name]: value
  });

}
editCliked(){
  const newProduct = {
    id:this.state.selectedProduct.id,
    name:this.state.editName,
    details:this.state.editDet,
    category:this.state.editCat,
    price:this.state.editPric
  };
  axios.put(`http://localhost:8080/` +this.state.selectedProduct.id, newProduct)
  .then(() => {
    this.setButtons();
  });
  this.setState({showEdit:false})

}

editCancel(){
  this.setState({showEdit:false})
}
addCancel(){
  this.setState({showAdd:false})
}

differentRender(){
  return (
    <div>
    <h2>Edit Product</h2>
    Name:
    <br/>
    <input type="text" name="editName"  value={this.state.editName} onChange={this.handleChange} />
    <br/>
    Details:
    <br/>
    <input type="text" name="editDet"value={this.state.editDet} onChange={this.handleChange}/>
    <br/>
    Category:
    <br/>
    <input type="text" name="editCat"value={this.state.editCat} onChange={this.handleChange} />
    <br/>
    Price:
    <br/>
    <input type="text" name="editPric" value={this.state.editPric} onChange={this.handleChange} />
    <br/>
    <button onClick={this.editCliked}>Edit</button>
    <button onClick={this.editCancel}>Cancel</button>
  </div>
  );
}
adderRender(){
  return(
  <div>
  <h2>Add Product</h2>
  Name:
  <br/>
  <input type="text" name="name" value={this.state.name} onChange={this.handleChange} />
  <br/>
  Details:
  <br/>
  <input type="text" name="details" value={this.state.details} onChange={this.handleChange}/>
  <br/>
  Category:
  <br/>
  <input type="text" name="category" value={this.state.category} onChange={this.handleChange}/>
  <br/>
  Price:
  <br/>
  <input type="text" name="price" value={this.state.price} onChange={this.handleChange} />
  <br/>
  <button onClick={this.handlePost}>Add</button>
  <button onClick={this.addCancel}>Cancel</button>
</div>
  );
}


handleEdit(v){
  
  this.setState({editName:v.name,
    editDet:v.details,
    editCat:v.category,
    editPric:v.price,
    showEdit:true,
  selectedProduct:v})

}
handleDelete(v){
  axios.delete(`http://localhost:8080/` +v.id)
  .then(() => {
    this.setButtons();
  });
}
handleButtonClick(v){
  this.setState({selectedProduct:v});
}

handlePost(){
  const product = {
    name:this.state.name,
    details:this.state.details,
    category:this.state.category,
    price:this.state.price
  };
  axios.post(this.urladd,product)
  .then(() => {
    this.setButtons();
    this.setState({showAdd:false})
  });

  

}
showAdder(){
  this.setState({showAdd:true})
}

render(){
  return(
    <div >
      {this.state.showEdit && <this.differentRender/>}
      {this.state.showAdd && <this.adderRender/>}
      <button className="addProd" onClick={this.showAdder}>Add Product</button>
      <div class="col-md-5">
      <table  className="asd"class="table table-hover">
      <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Details</th>
      <th scope="col">Category</th>
      <th scope="col">Price</th>
      <th scope="col">Actions</th>
    </tr>
  </thead>
  <tbody>
  {
      this.state.products.map(v => {
      return (
      <tr>
        <th scope="row">{v.id}</th> 
        <td>{v.name}</td>
        <td>{v.details}</td>
        <td>{v.category}</td>
        <td>{v.price}</td>
        <td><button onClick={() => this.handleEdit(v)}>Edit</button>
        <button onClick={() => this.handleDelete(v)}>Delete</button>
        </td>
        <br/>
         </tr>)
      })
    }
    </tbody>
    </table>
    </div>
    <div>

    </div>
      </div>
  );
}
}
function App() {
  return (
    <div>
    <Home/>
  </div>
  );
}
export default App;
