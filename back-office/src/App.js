
import './App.css';
import React, { Component } from "react";
import axios from 'axios';
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

    headers:null,
    loggedIn:false,
    username:'',
    password:'',
    productAdding:false,
    userAdding:false,

    users:[],
    userAddUserName:'',
    userAddPassWord:'',
    userAddRole:'',
    userEditUserName:'',
    userEditPassWord:'',
    userEditRole:'',
    userEditSelectedUserName:''
  };
this.handleButtonClick = this.handleButtonClick.bind(this);
this.handleDelete = this.handleDelete.bind(this);
this.setButtons = this.setButtons.bind(this);
this.handleEdit = this.handleEdit.bind(this);
this.differentRender = this.differentRender.bind(this);
this.handleChange = this.handleChange.bind(this);
this.editCliked = this.editCliked.bind(this);
this.handlePost = this.handlePost.bind(this);
this.adderRender = this.adderRender.bind(this);
this.ProductManager = this.ProductManager.bind(this);
this.UserManager = this.UserManager.bind(this);
this.ReportManager = this.ReportManager.bind(this);
this.UserAdder = this.UserAdder.bind(this);
this.UserEditer = this.UserEditer.bind(this);
this.handleUserAdd = this.handleUserAdd.bind(this);
this.handleUserEdit = this.handleUserEdit.bind(this);
this.handleUserDelete = this.handleUserDelete.bind(this);
this.editButtonClicked = this.editButtonClicked.bind(this);
this.mainDisplay = this.mainDisplay.bind(this);
this.loginScreenComp = this.loginScreenComp.bind(this);
this.handleLogin = this.handleLogin.bind(this);
this.userTableComp = this.userTableComp.bind(this);
this.changeUserAddingState = this.changeUserAddingState.bind(this);
this.RolesManager= this.RolesManager.bind(this);
this.changeProductAddingState = this.changeProductAddingState.bind(this);
this.productListDisp = this.productListDisp.bind(this);
}

setButtons(){
  axios.get(this.url +`/product/list`,{
    headers: this.state.headers
  })
  .then(res => {
    const prods = res.data;
    this.setState({products:prods });
    console.log(this.state.products)
  });
  axios.get(this.url +`/user/list`,{
    headers: this.state.headers
  })
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
  axios.put(`http://localhost:8080/product/` +this.state.selectedProduct.id +'/put', newProduct,{
    headers: this.state.headers
  })
  .then(() => {
    this.setButtons();
  });
  this.setState({showEdit:false})

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
    Picture:
    <br/>
    <input type="text" name="editUrl"  />
    <br/>
    <div className="AddCancel">
    <button class="btn btn-primary btn-sm" type="button"data-toggle="collapse" data-target="#editer" aria-expanded="false" aria-controls="editer" onClick={this.editCliked}>Edit</button>
    <button class="btn btn-secondary btn-sm" type="button"data-toggle="collapse" data-target="#editer" aria-expanded="false" aria-controls="Cancel">Cancel</button>
  </div>
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
  Picture:
    <br/>
    <input type="text" name="editUrl"/>
    <br/>
  <div className="AddCancel">
  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#adder" aria-expanded="false" aria-controls="adder"  onClick={this.handlePost}>Add</button>
  <button class="btn btn-secondary" type="button" data-toggle="collapse" data-target="#adder" aria-expanded="false" aria-controls="adder"  onClick={this.changeProductAddingState}>Cancel</button>
</div>
</div>
  );
}


handleEdit(v){
  this.setState({editName:v.name,
      editDet:v.details,
      editCat:v.category,
      editPric:v.price,
      selectedProduct:v});
      this.changeProductAddingState()
}

handleDelete(v){
  axios.delete(`http://localhost:8080/product/` +v.id  + '/delete',{
    headers: this.state.headers
  })
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
  axios.post(this.url + "/product/add/",product,{
    headers: this.state.headers
  })
  .then(() => {
    this.setButtons();
    this.setState({showAdd:false,
    productAdding:false})
  });

  

}


productListDisp(){
  return(
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
      <td><button onClick={() => this.handleEdit(v)} class="btn btn-primary btn-sm" type="button"data-toggle="collapse" data-target="#editer" aria-expanded="false"  aria-controls="editer">Edit</button>
      <button class="btn btn-danger btn-sm" type="button" onClick={() => this.handleDelete(v)}>Delete</button>
      <button class="btn btn-secondary btn-sm" type="button" >Details</button>
      </td>
       </tr>);
    })  
  }
  </tbody>
  </table>
  );
}

ProductManager(){
  return(
    <div class="container">
          <div class="d-flex justify-content-center">
      <div class="row row-cols-1">

      <div class="col">
      <div className="AddCancel">
    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#adder" aria-expanded="false" aria-controls="adder" onClick={this.changeProductAddingState} >Add Product</button>
    </div>
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
    {!this.state.productAdding && <this.productListDisp/>}
  </div>
  </div>
  <div>
  </div>
  </div>
  </div>
  </div>
  );
}
UserManager(){
  return (
    <div class="container">
      <div class="d-flex justify-content-center">
        <div class="row row-cols-1">
          <div class="col">
          <div className="AddCancel">
            <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#userAdd" aria-expanded="false" onClick={this.changeUserAddingState} aria-controls="userAdd">Add User</button>
            </div>
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
            {!this.state.userAdding && <this.userTableComp/>}
          </div>
        </div>
      </div>
    </div>
  );
}
RolesManager(){

}
ReportManager(){
  return (
    <div>

    </div>
  ); 
}

handleUserAdd(){
  const user = {
    userName:this.state.userAddUserName,
    passWord:this.state.userAddPassWord,
    role:this.state.userAddRole,
  };
  axios.post(this.url + '/user/add',user,{
    headers: this.state.headers
  })
  .then(() => {
    this.setButtons();
    this.setState({
      userAdding:false
    })
  });

  
}

handleUserEdit(){
  const newUser = {
    userName:this.state.userEditUserName,
    passWord:this.state.userEditPassWord,
    role:this.state.userEditRole
  };
  axios.put(`http://localhost:8080/` +this.state.userEditSelectedId + '/put', newUser,{
    headers: this.state.headers
  })
  .then(() => {
    this.setButtons();
  });
  this.setState({showEdit:false})
}

editButtonClicked(u){
  this.setState({userEditUserName:u.userName,
      userEditPassWord:u.passWord,
      userEditRole:u.role,
      userEditSelectedUsername:u.userName,
});
}

handleUserDelete(u){
  axios.delete(`http://localhost:8080/user/` +u.userName + '/delete',{
    headers: this.state.headers
  })
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
    <div className="AddCancel">
    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#userAdd" aria-expanded="false" aria-controls="userAdd"  onClick={this.handleUserAdd}>Add</button>
    <button class="btn btn-secondary" type="button" data-toggle="collapse" data-target="#userAdd" aria-expanded="false" aria-controls="userAdd" onClick={this.changeUserAddingState} >Cancel</button>
  </div>
  </div>
    );
}
UserEditer(){
  return(
    <div>
    <h3>Edit User</h3>
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
    <div className="AddCancel">
    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#userEditer" aria-expanded="false" aria-controls="userEditer"  onClick={this.handleUserEdit}>Save</button>
    <button class="btn btn-secondary" type="button" data-toggle="collapse" data-target="#userEditer" aria-expanded="false" aria-controls="userEditer">Cancel</button>
  </div>
  </div>
    );
}

userTableComp(){
  return(
    <table data-spy="scroll" class="table table-bordered table-hover">
    <thead  class="thead-dark">
      <tr>
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
              <td>{u.userName}</td>
              <td>{u.passWord}</td>
              <td>{u.role}</td>
              <td><button  onClick={() => this.editButtonClicked(u)}  class="btn btn-primary btn-sm" type="button"data-toggle="collapse" data-target="#userEditer" aria-expanded="false" aria-controls="userEditer">Edit</button>
              <button class="btn btn-danger btn-sm" type="button" onClick={() => this.handleUserDelete(u)}>Delete</button>
              <button class="btn btn-secondary btn-sm" type="button" >Details</button></td>
            </tr>
          );
        })
      }
    </tbody>
  </table>
  );
}


changeUserAddingState(){
  if(this.state.userAdding===true){
    this.setState({
      userAdding : false
    })
  }
  if(this.state.userAdding===false){
    this.setState({
      userAdding : true
    })
  }
}

changeProductAddingState(){
  if(this.state.productAdding===true){
    this.setState({
      productAdding : false
    })
  }
  if(this.state.productAdding===false){
    this.setState({
      productAdding : true
    })
  }
}


handleLogin(){
  let loginstr = this.state.username + ':' + this.state.password;
  let encodedString = new Buffer(loginstr).toString('base64');
  let head = {
    'Content-Type': 'application/json',
    'Authorization': 'Basic ' + encodedString
  };
  console.log(encodedString);
  this.setState({headers:head,
  loggedIn:true})
  axios.get(this.url + '/procuct/list',{
    headers:this.state.headers
  }).then(() =>{
    this.setButtons();
  })
}

loginScreenComp(){
  return(
    <div class="container">
      <div class="d-flex justify-content-center">
        <div className="loginCard">
          <div class="card  " >
            <div class="d-flex justify-content-center">
              <form onSubmit={this.handleLogin} className="loginForm ">
                <div class="form-group ">
                  <label for="usernameLogin" >User name</label>
                  <input type="usename" name="username" class="form-control" id="usernameLogin" value={this.state.username} onChange={this.handleChange}/>
                </div>
                  <div class="form-group">
                  <label for="pwLogin">Password</label>
                  <input type="password"  name="password" class="form-control" id="pwLogin" value={this.state.password} onChange={this.handleChange}/>
                </div>
                <button type="submit" class="btn btn-primary m-2">Login</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

mainDisplay(){
  return (
    <div>
      <nav>
        <div class="nav nav-tabs" id="nav-tab" role="tablist">
        <a class="nav-item nav-link active" id="nav-products-tab" data-toggle="tab" href="#nav-products" role="tab" aria-controls="nav-products" aria-selected="true">Products</a>
        <a class="nav-item nav-link" id="nav-categories-tab" data-toggle="tab" href="#nav-categories" role="tab" aria-controls="nav-categories" aria-selected="false">Categories</a>
        <a class="nav-item nav-link" id="nav-users-tab" data-toggle="tab" href="#nav-users" role="tab" aria-controls="nav-users" aria-selected="false">Users</a>
        <a class="nav-item nav-link" id="nav-roles-tab" data-toggle="tab" href="#nav-roles" role="tab" aria-controls="nav-roles" aria-selected="false">Roles</a>
        <a class="nav-item nav-link" id="nav-reports-tab" data-toggle="tab" href="#nav-reports" role="tab" aria-controls="nav-reports" aria-selected="false">Reports</a>
        </div>
      </nav>
      <div class="tab-content" id="nav-tabContent"> 
        <div class="tab-pane fade show active" id="nav-products" role="tabpanel" aria-labelledby="nav-products-tab"><this.ProductManager/></div>
        <div class="tab-pane fade" id="nav-users" role="tabpanel" aria-labelledby="nav-users-tab"><this.UserManager/></div>
        <div class="tab-pane fade" id="nav-categories" role="tabpanel" aria-labelledby="nav-categories-tab">...</div>
        <div class="tab-pane fade" id="nav-roles" role="tabpanel" aria-labelledby="nav-roles-tab">...</div>
        <div class="tab-pane fade" id="nav-reports" role="tabpanel" aria-labelledby="nav-reports-tab"><this.ReportManager/></div>
      </div>
    </div>

  );
}

render(){
  return(
    <div>
    {!this.state.loggedIn &&<this.loginScreenComp/>}
    {this.state.loggedIn && <this.mainDisplay/>}
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
