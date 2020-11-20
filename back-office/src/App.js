
import './App.css';
import React, { Component } from "react";

import axios from 'axios';
import {useState, useEffect} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css'
import 'jquery/dist/jquery.min.js'
import 'bootstrap/dist/js/bootstrap.min.js'



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
    showAdd:false,


    users:[],
    userAddUserName:'',
    userAddPassWord:'',
    userAddRole:'',
    userEditUserName:'',
    userEditPassWord:'',
    userEditRole:'',
    userEditSelectedId:0
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
this.ProductManager = this.ProductManager.bind(this);
this.UserManager = this.UserManager.bind(this);
this.ReportManager = this.ReportManager.bind(this);
this.UserAdder = this.UserAdder.bind(this);
this.UserEditer = this.UserEditer.bind(this);
this.handleUserAdd = this.handleUserAdd.bind(this);
this.handleUserEdit = this.handleUserEdit.bind(this);
this.handleUserDelete = this.handleUserDelete.bind(this);
this.editButtonClicked = this.editButtonClicked.bind(this);

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
  axios.get(this.url +`/user/list`)
  .then(res => {
    const userslst = res.data;
    this.setState({users:userslst });
    console.log(this.state.users)
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
    <h3>Edit Product</h3>
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
    <button class="btn btn-primary btn-sm" type="button"data-toggle="collapse" data-target="#editer" aria-expanded="false" aria-controls="editer" onClick={this.editCliked}>Edit</button>
    <button class="btn btn-secondary btn-sm" type="button"data-toggle="collapse" data-target="#editer" aria-expanded="false" aria-controls="Cancel">Cancel</button>
  </div>
  );
}
adderRender(){
  return(
  <div>
  <h3>Add Product</h3>
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
  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#adder" aria-expanded="false" aria-controls="adder"  onClick={this.handlePost}>Add</button>
  <button class="btn btn-secondary" type="button" data-toggle="collapse" data-target="#adder" aria-expanded="false" aria-controls="adder"  onClick={this.addCancel}>Cancel</button>
</div>
  );
}


handleEdit(v){
  this.setState({editName:v.name,
    editDet:v.details,
    editCat:v.category,
    editPric:v.price,
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


ProductManager(){
  return(
    <div class="container">
          <div class="d-flex justify-content-center">
      <div class="row row-cols-1">

      <div class="col">
    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#adder" aria-expanded="false" aria-controls="adder" >Add Product</button>
    <div class="collapse" id="adder">
  <div class="card card-body" >
  <this.adderRender/>
  </div>
  </div> 
  <div class="collapse" id="editer">
  <div class="card card-body">
  <this.differentRender/>
  </div>
  </div>
    </div>

    <div class="col">
    <div>
    <table  data-spy="scroll" class="table table-bordered table-hover">
    <thead  class="thead-dark">
  <tr>
    <th scope="col">ID</th>
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
      <td><button onClick={() => this.handleEdit(v)} class="btn btn-primary btn-sm" type="button"data-toggle="collapse" data-target="#editer" aria-expanded="false" aria-controls="editer">Edit</button>
      <button class="btn btn-danger btn-sm" type="button" onClick={() => this.handleDelete(v)}>Delete</button>
      </td>
       </tr>);
    })
  }
  </tbody>
  </table>
  </div>
  </div>
  <div>
  </div>
  </div>
  </div>
  </div>
  );
}

handleUserAdd(){
  const user = {
    userName:this.state.userAddUserName,
    passWord:this.state.userAddPassWord,
    role:this.state.userAddRole,
  };
  axios.post(this.url + '/user/add',user)
  .then(() => {
    this.setButtons();
  });

  
}

handleUserEdit(){
  const newUser = {
    userName:this.state.userEditUserName,
    passWord:this.state.userEditPassWord,
    role:this.state.userEditRole
  };
  axios.put(`http://localhost:8080/` +this.state.userEditSelectedId, newUser)
  .then(() => {
    this.setButtons();
  });
  this.setState({showEdit:false})
}

editButtonClicked(u){
  this.setState({userEditUserName:u.userName,
      userEditPassWord:u.passWord,
      userEditRole:u.role,
      userEditSelectedId:u.id
});
}

handleUserDelete(u){
  axios.delete(`http://localhost:8080/user/` +u.id)
  .then(() => {
    this.setButtons();
  });
}


UserAdder(){
  return(
    <div>
    <h3>Add User</h3>
    Username:
    <br/>
    <input type="text" name="userAddUserName" value={this.state.userAddUserName} onChange={this.handleChange} />
    <br/>
    {//UwU
    }
    PassWord:
    <br/>
    <input type="text" name="userAddPassWord" value={this.state.userAddPassWord} onChange={this.handleChange}/>
    <br/>
    Role:
    <br/>
    <input type="text" name="userAddRole" value={this.state.userAddRole} onChange={this.handleChange}/>

    <br/>
    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#userAdd" aria-expanded="false" aria-controls="userAdd"  onClick={this.handleUserAdd}>Add</button>
    <button class="btn btn-secondary" type="button" data-toggle="collapse" data-target="#userAdd" aria-expanded="false" aria-controls="userAdd" >Cancel</button>
  </div>
    );
}
UserEditer(){
  return(
    <div>
    <h3>Add User</h3>
    Username:
    <br/>
    <input type="text" name="userEditUserName" value={this.state.userEditUserName} onChange={this.handleChange} />
    <br/>
    PassWord:
    <br/>
    <input type="text" name="userEditPassWord" value={this.state.userEditPassWord} onChange={this.handleChange}/>
    <br/>
    Role:
    <br/>
    <input type="text" name="userEditRole" value={this.state.userEditRole} onChange={this.handleChange}/>
    <br/>
    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#userEditer" aria-expanded="false" aria-controls="userEditer"  onClick={this.handleUserEdit}>Add</button>
    <button class="btn btn-secondary" type="button" data-toggle="collapse" data-target="#userEditer" aria-expanded="false" aria-controls="userEditer">Cancel</button>
  </div>
    );
}

UserManager(){
  return (
    <div class="container">
      <div class="d-flex justify-content-center">
        <div class="row row-cols-1">
          <div class="col">
            <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#userAdd" aria-expanded="false" aria-controls="userAdd">Add User</button>
            <div class="collapse" id="userAdd">
              <div class="card card-body">
                <this.UserAdder/>
              </div>
            </div> 
            <div class="collapse" id="userEditer">
              <div class="card card-body">
                <this.UserEditer/>
              </div>
            </div>
          </div>
          <div class="col">
            <table data-spy="scroll" class="table table-bordered table-hover">
              <thead  class="thead-dark">
                <tr>
                  <th>ID</th>
                  <th>Username</th>
                  <th>Password</th>
                  <th>Role</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {
                  this.state.users.map(u => {
                    return (
                      <tr>
                        <td>{u.id}</td>
                        <td>{u.userName}</td>
                        <td>{u.passWord}</td>
                        <td>{u.role}</td>
                        <td><button  onClick={() => this.editButtonClicked(u)}  class="btn btn-primary btn-sm" type="button"data-toggle="collapse" data-target="#userEditer" aria-expanded="false" aria-controls="userEditer">Edit</button>
                        <button class="btn btn-danger btn-sm" type="button" onClick={() => this.handleUserDelete(u)}>Delete</button></td>
                      </tr>
                    );
                  })
                }
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
}

ReportManager(){
  return (
    <div>

    </div>
  );
    
}

render(){
  return(
    <div>
      <nav>
  <div class="nav nav-tabs" id="nav-tab" role="tablist">
    <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Products</a>
    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">Users</a>
    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">Reports</a>
    </div>

    </nav>
    <div class="tab-content" id="nav-tabContent">
      <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab"><this.ProductManager/></div>
      <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab"><this.UserManager/></div>
      <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab"><this.ReportManager/></div>
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
