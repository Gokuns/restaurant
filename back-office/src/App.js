
import './App.css';
import React, { Component } from "react";
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css'
import 'jquery/dist/jquery.min.js'
import 'bootstrap/dist/js/bootstrap.min.js'



class Home extends Component {

  constructor(props) {
    super(props);
    this.url = "http://localhost:8080";
    this.urladd = "http://localhost:8080/add/";



    this.state = {
      products: [],
      selectedProduct: null,
      editName: '',
      editDet: '',
      editCat: '',
      editPric: '',
      name: '',
      details: '',
      category: 0,
      price: 0,
      showEdit: false,
      showAdd: false,

      headers: null,
      loggedIn: false,
      username: '',
      password: '',
      productAdding: false,
      userAdding: false,
      categoryAdding: false,

      categoryName: '',
      categoryId: 0,
      categories: [],

      tableCategoryAdding: false,
      tableCategoryId: 0,
      tableCategoryName: '',
      tableCategories: [],
      tableCat: 0,

      tableId:0,
      editTableCatId:0,
      tables:[],



      infos: { 'a': '123' },


      users: [],
      userAddUserName: '',
      userAddPassWord: '',
      userAddRole: '',
      userEditUserName: '',
      userEditPassWord: '',
      userEditRole: '',
      userEditSelectedUserName: ''
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
    this.RolesManager = this.RolesManager.bind(this);
    this.changeProductAddingState = this.changeProductAddingState.bind(this);
    this.productListDisp = this.productListDisp.bind(this);
    this.CategoriesComponent = this.CategoriesComponent.bind(this);
    this.changeCategoryAddingState = this.changeCategoryAddingState.bind(this);
    this.CategoryTableComponent = this.CategoryTableComponent.bind(this);
    this.CategoryAdder = this.CategoryAdder.bind(this);
    this.CategoryEditer = this.CategoryEditer.bind(this);
    this.refreshSite = this.refreshSite.bind(this);
    this.handleCategoryAdd = this.handleCategoryAdd.bind(this);
    this.handleCategoryEdit = this.handleCategoryEdit.bind(this);
    this.categoryEditClicked = this.categoryEditClicked.bind(this);
    this.handleCategoryDelete = this.handleCategoryDelete.bind(this);
    this.getItemById = this.getItemById.bind(this);
    this.InfoComponent = this.InfoComponent.bind(this);
    this.TablesComp = this.TablesComp.bind(this);
  }



  setButtons() {
    axios.get(this.url + `/product/list`, {
      headers: this.state.headers
    })
      .then(res => {
        const prods = res.data;
        this.setState({ products: prods });
        console.log(this.state.products)
      });
    axios.get(this.url + `/user/list`, {
      headers: this.state.headers
    })
      .then(res => {
        const userslst = res.data;
        this.setState({ users: userslst });

      });
    axios.get(this.url + `/category/list`, {
      headers: this.state.headers
    })
      .then(res => {
        const cats = res.data;
        this.setState({ categories: cats });
      });
    axios.get(this.url + `/info/list`, {
      headers: this.state.headers
    })
      .then(res => {
        const infa = res.data;
        console.log(infa)
        this.setState({ infos: res.data });
      });
    axios.get(this.url + `/table_category/list`, {
      headers: this.state.headers
    })
      .then(res => {
        const cats = res.data;
        this.setState({ tableCategories: cats });
      });
      axios.get(this.url + `/table/list`, {
        headers: this.state.headers
      })
        .then(res => {
          const tabs = res.data;
          this.setState({ tables: tabs });
        });


  }


  refreshSite() {
    this.setButtons();
  }

  handleChange(event) {
    const target = event.target;
    const name = target.name;
    const value = target.value;

    this.setState({
      [name]: value
    });
  }

//PRODUCT-------------------------------------------------------------------------------------------------------------------------------------------------------------------

  editCliked() {
    const newProduct = {
      id: this.state.selectedProduct.id,
      name: this.state.editName,
      details: this.state.editDet,
      category: this.state.editCat,
      price: this.state.editPric
    };
    axios.put(`http://localhost:8080/product/` + this.state.selectedProduct.id + '/put', newProduct, {
      headers: this.state.headers
    })
      .then(() => {
        this.setButtons();
      });
    this.setState({ showEdit: false })

  }

  differentRender() {
    return (
      <div>
        <h3>Edit Product</h3>
    Name:
        <br />
        <input type="text" name="editName" value={this.state.editName} onChange={this.handleChange} />
        <br />
    Details:
        <br />
        <input type="text" name="editDet" value={this.state.editDet} onChange={this.handleChange} />
        <br />
    Category:
        <br />
        <input type="text" name="editCat" value={this.state.editCat} onChange={this.handleChange} />
        <br />
    Price:
        <br />
        <input type="text" name="editPric" value={this.state.editPric} onChange={this.handleChange} />
        <br />
    Picture:
        <br />
        <input type="text" name="editUrl" />
        <br />
        <div className="AddCancel">
          <button class="btn btn-primary btn-sm" type="button" data-toggle="collapse" data-target="#editer" aria-expanded="false" aria-controls="editer" onClick={this.editCliked}>Edit</button>
          <button class="btn btn-secondary btn-sm" type="button" data-toggle="collapse" data-target="#editer" aria-expanded="false" aria-controls="Cancel">Cancel</button>
        </div>
      </div>
    );
  }
  adderRender() {
    return (
      <div>
        <h3>Add Product</h3>
  Name:
        <br />
        <input type="text" name="name" value={this.state.name} onChange={this.handleChange} />
        <br />
  Details:
        <br />
        <input type="text" name="details" value={this.state.details} onChange={this.handleChange} />
        <br />
  Category:
        <br />
        <input type="number" name="category" value={this.state.category} onChange={this.handleChange} />
        <br />
  Price:
        <br />
        <input type="text" name="price" value={this.state.price} onChange={this.handleChange} />
        <br />
  Picture:
        <br />
        <input type="text" name="editUrl" />
        <br />
        <div className="AddCancel">
          <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#adder" aria-expanded="false" aria-controls="adder" onClick={this.handlePost}>Add</button>
          <button class="btn btn-secondary" type="button" data-toggle="collapse" data-target="#adder" aria-expanded="false" aria-controls="adder" onClick={this.changeProductAddingState}>Cancel</button>
        </div>
      </div>
    );
  }


  handleEdit(v) {
    this.setState({
      editName: v.name,
      editDet: v.details,
      editCat: v.category,
      editPric: v.price,
      selectedProduct: v
    });
  }

  handleDelete(v) {
    axios.delete(`http://localhost:8080/product/` + v.id + '/delete', {
      headers: this.state.headers
    })
      .then(() => {
        this.setButtons();
      });
  }
  handleButtonClick(v) {
    this.setState({ selectedProduct: v });
  }

  handlePost() {
    const product = {
      name: this.state.name,
      details: this.state.details,
      price: this.state.price
    };
    axios.post(this.url + "/product/add/" + this.state.category, product, {
      headers: this.state.headers
    })
      .then(() => {
        this.setButtons();
        this.setState({
          showAdd: false,
          productAdding: false
        })
      });



  }


  getItemById(id) {

    let response = this.state.categories.filter(c => {
      if (id === c.id) {
        return c;
      }
    });
    if (response.length != 0) {
      return response[0];
    }
    else return '';
  }

  changeFilterState = (id) => {
    let res = this.state.products.filter(c => {
      if (id == c.cat) {
        return c;
      }
    })
    if (res.length != 0) {
      this.setState({ products: res })
    }
  }

  productListDisp() {
    return (
      <table data-spy="scroll" class="table table-bordered table-hover">
        <thead class="thead-dark">
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
                  <td><a href="#" onClick={() => this.changeFilterState(v.cat)}>{this.getItemById(v.cat).name}</a></td>
                  <td>{v.price}</td>
                  <td><button onClick={() => this.handleEdit(v)} class="btn btn-primary btn-sm" type="button" data-toggle="collapse" data-target="#editer" aria-expanded="false" aria-controls="editer">Edit</button>
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

  ProductManager() {
    return (
      <div class="container">
        <div class="d-flex justify-content-center">
          <div class="row row-cols-1">

            <div class="col">
              <div className="AddCancel">
                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#adder" aria-expanded="false" aria-controls="adder" onClick={this.changeProductAddingState} >Add Product</button>
              </div>
              <div class="collapse" id="adder">
                <div class="card card-body" >
                  <this.adderRender />
                </div>
              </div>
              <div class="collapse" id="editer">
                <div class="card card-body">
                  <this.differentRender />
                </div>
              </div>
            </div>
            <div class="col">
              <div>
                {!this.state.productAdding && <this.productListDisp />}
              </div>
            </div>
            <div>
            </div>
          </div>
        </div>
      </div>
    );
  }

//MANAGER-------------------------------------------------------------------------------------------------------------------------------------------------------------------

  UserManager() {
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
                  <this.UserAdder />
                </div>
              </div>
              <div class="collapse" id="userEditer">
                <div class="card card-body">
                  <this.UserEditer />
                </div>
              </div>
            </div>
            <div class="col">
              {!this.state.userAdding && <this.userTableComp />}
            </div>
          </div>
        </div>
      </div>
    );
  }


  RolesManager() {

  }


  ReportManager() {
    return (
      <div>

      </div>
    );
  }

//USER-------------------------------------------------------------------------------------------------------------------------------------------------------------------

  handleUserAdd() {
    const user = {
      userName: this.state.userAddUserName,
      passWord: this.state.userAddPassWord,
      role: this.state.userAddRole,
    };
    axios.post(this.url + '/user/add', user, {
      headers: this.state.headers
    })
      .then(() => {
        this.setButtons();
        this.setState({
          userAdding: false
        })
      });


  }

  handleUserEdit() {
    const newUser = {
      userName: this.state.userEditUserName,
      passWord: this.state.userEditPassWord,
      role: this.state.userEditRole
    };
    axios.put(`http://localhost:8080/` + this.state.userEditSelectedId + '/put', newUser, {
      headers: this.state.headers
    })
      .then(() => {
        this.setButtons();
      });
    this.setState({ showEdit: false })
  }

  editButtonClicked(u) {
    this.setState({
      userEditUserName: u.userName,
      userEditPassWord: u.passWord,
      userEditRole: u.role,
      userEditSelectedUsername: u.userName,
    });
  }

  handleUserDelete(u) {
    axios.delete(`http://localhost:8080/user/` + u.userName + '/delete', {
      headers: this.state.headers
    })
      .then(() => {
        this.setButtons();
      });
  }


  UserAdder() {

    return (
      <div>
        <h3>Add User</h3>
    Username:
        <br />
        <input type="text" name="userAddUserName" value={this.state.userAddUserName} onChange={this.handleChange} />
        <br />
        {//UwU
        }
    PassWord:
        <br />
        <input type="text" name="userAddPassWord" value={this.state.userAddPassWord} onChange={this.handleChange} />
        <br />
    Role:
        <br />
        <input type="text" name="userAddRole" value={this.state.userAddRole} onChange={this.handleChange} />

        <br />
        <div className="AddCancel">
          <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#userAdd" aria-expanded="false" aria-controls="userAdd" onClick={this.handleUserAdd}>Add</button>
          <button class="btn btn-secondary" type="button" data-toggle="collapse" data-target="#userAdd" aria-expanded="false" aria-controls="userAdd" onClick={this.changeUserAddingState} >Cancel</button>
        </div>
      </div>
    );
  }
  UserEditer() {
    return (
      <div>
        <h3>Edit User</h3>
    Username:
        <br />
        <input type="text" name="userEditUserName" value={this.state.userEditUserName} onChange={this.handleChange} />
        <br />
    PassWord:
        <br />
        <input type="text" name="userEditPassWord" value={this.state.userEditPassWord} onChange={this.handleChange} />
        <br />
    Role:
        <br />
        <input type="text" name="userEditRole" value={this.state.userEditRole} onChange={this.handleChange} />
        <br />
        <div className="AddCancel">
          <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#userEditer" aria-expanded="false" aria-controls="userEditer" onClick={this.handleUserEdit}>Save</button>
          <button class="btn btn-secondary" type="button" data-toggle="collapse" data-target="#userEditer" aria-expanded="false" aria-controls="userEditer">Cancel</button>
        </div>
      </div>
    );
  }

  userTableComp() {
    return (
      <table data-spy="scroll" class="table table-bordered table-hover">
        <thead class="thead-dark">
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
                  <td><button onClick={() => this.editButtonClicked(u)} class="btn btn-primary btn-sm" type="button" data-toggle="collapse" data-target="#userEditer" aria-expanded="false" aria-controls="userEditer">Edit</button>
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


  changeUserAddingState() {
    if (this.state.userAdding === true) {
      this.setState({
        userAdding: false
      })
    }
    if (this.state.userAdding === false) {
      this.setState({
        userAdding: true
      })
    }
  }

  changeProductAddingState() {
    if (this.state.productAdding === true) {
      this.setState({
        productAdding: false
      })
    }
    if (this.state.productAdding === false) {
      this.setState({
        productAdding: true
      })
    }
  }

  //LOGIN-------------------------------------------------------------------------------------------------------------------------------------------------------------------

  handleLogin() {
    let loginstr = this.state.username + ':' + this.state.password;
    let encodedString = new Buffer(loginstr).toString('base64');
    let head = {
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + encodedString
    };
    console.log(encodedString);
    this.setState({
      headers: head,
      loggedIn: true
    })
  }

  setLoggedInTrue = () => {
    this.setState({ loggedIn: true })
  }

  loginScreenComp() {
    return (
      <div class="container">
        <div class="d-flex justify-content-center">
          <div className="loginCard">
            <div class="card  " >
              <div class="d-flex justify-content-center">
                <form onSubmit={this.handleLogin} className="loginForm ">
                  <div class="form-group ">
                    <label for="usernameLogin" >User name</label>
                    <input type="usename" name="username" class="form-control" id="usernameLogin" value={this.state.username} onChange={this.handleChange} />
                  </div>
                  <div class="form-group">
                    <label for="pwLogin">Password</label>
                    <input type="password" name="password" class="form-control" id="pwLogin" value={this.state.password} onChange={this.handleChange} />
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

//CATEGORY-------------------------------------------------------------------------------------------------------------------------------------------------------------------

  changeCategoryAddingState() {
    if (this.state.categoryAdding === true) {
      this.setState({
        categoryAdding: false
      })
    }
    if (this.state.categoryAdding === false) {
      this.setState({
        categoryAdding: true
      })
    }
  }

  handleCategoryAdd() {

    axios.post('http://localhost:8080/category/add/' + this.state.categoryName, null, {
      headers: this.state.headers
    })
      .then(() => {
        this.setButtons();
        this.setState({
          categoryAdding: false
        })
      });
  }


  handleCategoryEdit() {
    axios.put(`http://localhost:8080/category/` + this.state.categoryId + '/put/' + this.state.categoryName, null, {
      headers: this.state.headers
    })
      .then(() => {
        this.setButtons();
      });
    this.setState({ showEdit: false })
  }

  categoryEditClicked(v) {
    this.setState({
      categoryName: v.name,
      categoryId: v.id
    });
  }

  handleCategoryDelete(u) {
    axios.delete(`http://localhost:8080/category/` + u.id + '/delete', {
      headers: this.state.headers
    })
      .then(() => {
        this.setButtons();
      });
  }

  CategoryAdder() {
    return (
      <div>
        <h3>Add Category</h3>
    Name:
        <br />
        <input type="text" name="categoryName" value={this.state.categoryName} onChange={this.handleChange} />
        <br />
        <div className="AddCancel">
          <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#categoryAdder" aria-expanded="false" aria-controls="categoryAdder" onClick={this.handleCategoryAdd}>Add</button>
          <button class="btn btn-secondary" type="button" data-toggle="collapse" data-target="#categoryAdder" aria-expanded="false" aria-controls="categoryAdder" onClick={this.changeCategoryAddingState} >Cancel</button>
        </div>
      </div>
    );
  }

  CategoryEditer() {
    return (
      <div>
        <h3>Edit Category</h3>
    Name:
        <br />
        <input type="text" name="categoryName" value={this.state.categoryName} onChange={this.handleChange} />
        <br />
        <div className="AddCancel">
          <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#categoryEditer" aria-expanded="false" aria-controls="categoryEditer" onClick={this.handleCategoryEdit}>Save</button>
          <button class="btn btn-secondary" type="button" data-toggle="collapse" data-target="#categoryEditer" aria-expanded="false" aria-controls="categoryEditer" >Cancel</button>
        </div>
      </div>
    );
  }

  CategoryTableComponent() {
    return (
      <table data-spy="scroll" class="table table-bordered table-hover">
        <thead class="thead-dark">
          <tr>
            <th>id</th>
            <th>Name</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {
            this.state.categories.map(u => {
              return (
                <tr>
                  <td>{u.id}</td>
                  <td>{u.name}</td>
                  <td><button onClick={() => this.categoryEditClicked(u)} class="btn btn-primary btn-sm" type="button" data-toggle="collapse" data-target="#categoryEditer" aria-expanded="false" aria-controls="categoryEditer">Edit</button>
                    <button class="btn btn-danger btn-sm" type="button" onClick={() => this.handleCategoryDelete(u)}>Delete</button>
                    <button class="btn btn-secondary btn-sm" type="button" >Details</button></td>
                </tr>
              );
            })
          }
        </tbody>
      </table>
    );
  }

  CategoriesComponent() {
    return (
      <div class="container">
        <div class="d-flex justify-content-center">
          <div class="row row-cols-1">
            <div class="col">
              <div className="AddCancel">
                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#categoryAdder" aria-expanded="false" onClick={this.changeCategoryAddingState} aria-controls="categoryAdder">Add Category</button>
              </div>
              <div class="collapse" id="categoryAdder">
                <div class="card card-body">
                  <this.CategoryAdder />
                </div>
              </div>
              <div class="collapse" id="categoryEditer">
                <div class="card card-body">
                  <this.CategoryEditer />
                </div>
              </div>
            </div>
            <div class="col">
              {!this.state.categoryAdding && <this.CategoryTableComponent />}
            </div>
          </div>
        </div>
      </div>
    );
  }
//INFO-------------------------------------------------------------------------------------------------------------------------------------------------------------------

  InfoComponent() {
    return (
      <div className="container mt-2 d-flex justify-content-center">
        <div className="row">
          <table className="table table-bordered table-hover">
            <tr>
              <td>server.port</td>      <td>{'' + this.state.infos.port}</td>
            </tr>
            <tr>
              <td>spring.application.name</td>      <td>{'' + this.state.infos.name}</td>
            </tr>
            <tr>
              <td>spring.h2.console.enabled</td>      <td>{'' + this.state.infos.enabled}</td>
            </tr>
            <tr>
              <td>spring.jpa.hibernate.dll-auto</td>      <td>{'' + this.state.infos.dll_auto}</td>
            </tr>
            <tr>
              <td>spring.datasource.url</td>      <td>{'' + this.state.infos.url}</td>
            </tr>
            <tr>
              <td>spring.jpa.show.sql</td>      <td>{'' + this.state.infos.show_sql}</td>
            </tr>
            <tr>
              <td>spring.jpa.properties.hibernate.format_sql</td>      <td>{'' + this.state.infos.format_sql}</td>
            </tr>
            <tr>
              <td>logging.level.org.hibernate.type</td>      <td>{'' + this.state.infos.hibernate_type}</td>
            </tr>
            <tr>
              <td>spring.datasource.driverClassName</td>      <td>{'' + this.state.infos.driverClassName}</td>
            </tr>
            <tr>
              <td>spring.datasource.username</td>      <td>{'' + this.state.infos.username}</td>
            </tr>
            <tr>
              <td>spring.datasource.password</td>      <td>{'' + this.state.infos.password}</td>
            </tr>
            <tr>
              <td>spring.jpa.properties.hibernate.hbm2ddl.auto</td>      <td>{'' + this.state.infos.hbm2ddl_auto}</td>
            </tr>
            <tr>
              <td>spring.datasource.initialization-mode</td>      <td>{'' + this.state.infos.initialization_mode}</td>
            </tr>

          </table>
        </div>
      </div>
    );
  }

//TABLE-------------------------------------------------------------------------------------------------------------------------------------------------------------------

changeProductAddingState() {
  if (this.state.tableAdding === true) {
    this.setState({
      tableAdding: false
    })
  }
  if (this.state.tableAdding === false) {
    this.setState({
      tableAdding: true
    })
  }
}

tableEditCliked = () => {

  axios.put(`http://localhost:8080/table/` + this.state.tableId + '/put/' + this.state.editTableCatId, null, {
    headers: this.state.headers
  })
    .then(() => {
      this.setButtons();
    });
  this.setState({ showEdit: false })

}

TableEditer = () => {
  return (
    <div>
      <h3>Edit Product</h3>
  Category:
      <br />
      <input type="text" name="editTableCatId" value={this.state.editTableCatId} onChange={this.handleChange} />
      <br />
      <div className="AddCancel">
        <button class="btn btn-primary btn-sm" type="button" data-toggle="collapse" data-target="#tableEdited" aria-expanded="false" aria-controls="tableEdited" onClick={this.tableEditCliked}>Edit</button>
        <button class="btn btn-secondary btn-sm" type="button" data-toggle="collapse" data-target="#tableEdited" aria-expanded="false" aria-controls="tableEdited">Cancel</button>
      </div>
    </div>
  );
}
TableAdder = () => {
  return (
    <div>
      <h3>Add Table</h3>
      Category:
      <br />
      <input type="number" name="tableCatId" value={this.state.tableCatId} onChange={this.handleChange} />
      <br />
      <div className="AddCancel">
        <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#tableAdder" aria-expanded="false" aria-controls="tableAdder" onClick={this.handleTablePost}>Add</button>
        <button class="btn btn-secondary" type="button" data-toggle="collapse" data-target="#tableAdder" aria-expanded="false" aria-controls="tableAdder" onClick={this.changeTableAddingState}>Cancel</button>
      </div>
    </div>
  );
}



handleTableEdit = (v) => {
  this.setState({
    selectedTable: v,
    editTableCatId:v.tableCatId,
    tableId: v.tableId
  });
}

handleTableDelete = (v) => {
  axios.delete(`http://localhost:8080/table/` + v.tableCatId + '/delete', {
    headers: this.state.headers
  })
    .then(() => {
      this.setButtons();
    });
} 

handleButtonClick = (v) => {
  this.setState({ selectedTable: v });
}


handleTablePost = () => {
  axios.post(this.url + "/table/add/" + this.state.tableCatId, null, {
    headers: this.state.headers
  })
    .then(() => {
      this.setButtons();
      this.setState({
        tableAdding: false
      })
    });



}

  getTableItemById = (id) => {

    let response = this.state.tableCategories.filter(c => {
      if (id === c.id) {
        return c;
      }
    });
    if (response.length != 0) {
      return response[0];
    }
    else return '';
  }

  changeTableFilterState = (id) => {
    let res = this.state.tables.filter(c => {
      if (id == c.tableCatId) {
        return c;
      }
    })
    if (res.length != 0) {
      this.setState({ tables: res })
    }
  }


  TableTableComp = () => {
    return (
      <table data-spy="scroll" class="table table-bordered table-hover">
        <thead class="thead-dark">
          <tr>
            <th scope="col">ID</th>

            <th scope="col">Category</th>

            <th scope="col">Actions</th>
          </tr>
        </thead>
        <tbody>
          {
            this.state.tables.map(v => {
              return (
                <tr>
                  <th scope="row">{v.tableId}</th>
                  <td><a href="#" onClick={() => this.changeTableFilterState(v.tableCatId)}>{this.getTableItemById(v.tableCatId).name}</a></td>
                  <td><button onClick={() => this.handleTableEdit(v)} class="btn btn-primary btn-sm" type="button" data-toggle="collapse" data-target="#tableEditer" aria-expanded="false" aria-controls="tableEditer">Edit</button>
                    <button class="btn btn-danger btn-sm" type="button" onClick={() => this.handleTableDelete(v)}>Delete</button>
                    <button class="btn btn-secondary btn-sm" type="button" >Details</button>
                  </td>
                </tr>);
            })
          }
        </tbody>
      </table>
    );
  }

  TablesComp = () => {
    return (
      <div class="container">
        <div >
          <div class="row row-cols-1">

            <div class="col">
              <div className="AddCancel">
                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#tableAdder" aria-expanded="false" aria-controls="tableAdder" onClick={this.changeTableAddingState} >Add Table</button>
              </div>
              <div class="collapse" id="tableAdder">
                <div class="card card-body" >
                  <this.TableAdder />
                </div>
              </div>
              <div class="collapse" id="tableEditer">
                <div class="card card-body">
                  <this.TableEditer />
                </div>
              </div>
            </div>
            <div class="col">
              <div>
                {!this.state.tableAdding && <this.TableTableComp />}
              </div>
            </div>
            <div>
            </div>
          </div>
        </div>
      </div>
    );
  }


//TABLE CATEGORY-----------------------------------------------------------------------------------------------------------------------------------------------------------------


  changeTableCategoryAddingState = () => {
    if (this.state.tableCategoryAdding === true) {
      this.setState({
        tableCategoryAdding: false
      })
    }
    if (this.state.tableCategoryAdding === false) {
      this.setState({
        tableCategoryAdding: true
      })
    }
  }

  handleTableCategoryAdd = () => {
    let newCat = {
      name: this.state.tableCategoryName
    };

    axios.post('http://localhost:8080/table_category/add/', newCat, {
      headers: this.state.headers
    })
      .then(() => {
        this.setButtons();
        this.setState({
          tableCategoryAdding: false
        })
      });
  }


  handleTableCategoryEdit = () => {
    let newCat = {
      id: this.state.tableCategoryId,
      name: this.state.tableCategoryName
    }
    axios.put(`http://localhost:8080/table_category/` + this.state.tableCategoryId + '/put/', newCat, {
      headers: this.state.headers
    })
      .then(() => {
        this.setButtons();
      });
    this.setState({ showEdit: false })
  }

  tableCategoryEditClicked = (v) => {
    this.setState({
      tableCategoryName: v.name,
      tableCategoryId: v.id
    });
  }

  handleTableCategoryDelete = (u) => {
    axios.delete(`http://localhost:8080/table_category/` + u.id + '/delete', {
      headers: this.state.headers
    })
      .then(() => {
        this.setButtons();
      });
  }

  TableCategoryAdder = () => {
    return (
      <div>
        <h3>Add Table Category</h3>
          Name:
        <br />
        <input type="text" name="tableCategoryName" value={this.state.tableCategoryName} onChange={this.handleChange} />
        <br />
        <div className="AddCancel">
          <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#tableCategoryAdder" aria-expanded="false" aria-controls="tableCategoryAdder" onClick={this.handleTableCategoryAdd}>Add</button>
          <button class="btn btn-secondary" type="button" data-toggle="collapse" data-target="#tableCategoryAdder" aria-expanded="false" aria-controls="tableCategoryAdder" onClick={this.changeTableCategoryAddingState} >Cancel</button>
        </div>
      </div>
    );
  }

  TableCategoryEditer = () => {
    return (
      <div>
        <h3>Edit Table Category</h3>
    Name:
        <br />
        <input type="text" name="tableCategoryName" value={this.state.tableCategoryName} onChange={this.handleChange} />
        <br />
        <div className="AddCancel">
          <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#tableCategoryEditer" aria-expanded="false" aria-controls="tableCategoryEditer" onClick={this.handleTableCategoryEdit}>Save</button>
          <button class="btn btn-secondary" type="button" data-toggle="collapse" data-target="#tableCategoryEditer" aria-expanded="false" aria-controls="tableCategoryEditer" >Cancel</button>
        </div>
      </div>
    );
  }

  TableCategoryTableComponent = () => {
    return (
      <table data-spy="scroll" class="table table-bordered table-hover">
        <thead class="thead-dark">
          <tr>
            <th>id</th>
            <th>Name</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {
            this.state.tableCategories.map(u => {
              return (
                <tr>
                  <td>{u.id}</td>
                  <td>{u.name}</td>
                  <td><button onClick={() => this.tableCategoryEditClicked(u)} class="btn btn-primary btn-sm" type="button" data-toggle="collapse" data-target="#tableCategoryEditer" aria-expanded="false" aria-controls="tableCategoryEditer">Edit</button>
                    <button class="btn btn-danger btn-sm" type="button" onClick={() => this.handleTableCategoryDelete(u)}>Delete</button>
                    <button class="btn btn-secondary btn-sm" type="button" >Details</button></td>
                </tr>
              );
            })
          }
        </tbody>
      </table>
    );
  }

  TableCategoyComp = () => {
    return (
      <div class="container">
        <div>
          <div class="row row-cols-1">
            <div class="col">
              <div className="AddCancel">
                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#tableCategoryAdder" aria-expanded="false" onClick={this.changeTableCategoryAddingState} aria-controls="categoryAdder">Add Category</button>
              </div>
              <div class="collapse" id="tableCategoryAdder">
                <div class="card card-body">
                  <this.TableCategoryAdder />
                </div>
              </div>
              <div class="collapse" id="tableCategoryEditer">
                <div class="card card-body">
                  <this.TableCategoryEditer />
                </div>
              </div>
            </div>
            <div class="col">
              {!this.state.tableCategoryAdding && <this.TableCategoryTableComponent />}
            </div>
          </div>
        </div>
      </div>
    );
  }




  mainDisplay() {
    return (
      <div>
        <nav>
          <div class="nav nav-tabs" id="nav-tab" role="tablist">
            <a class="nav-item nav-link active" id="nav-products-tab" data-toggle="tab" href="#nav-products" role="tab" aria-controls="nav-products" aria-selected="true">Products</a>
            <a class="nav-item nav-link" id="nav-categories-tab" data-toggle="tab" href="#nav-categories" role="tab" aria-controls="nav-categories" aria-selected="false">Categories</a>
            <a class="nav-item nav-link" id="nav-users-tab" data-toggle="tab" href="#nav-users" role="tab" aria-controls="nav-users" aria-selected="false">Users</a>
            <a class="nav-item nav-link" id="nav-roles-tab" data-toggle="tab" href="#nav-roles" role="tab" aria-controls="nav-roles" aria-selected="false">Roles</a>
            <a class="nav-item nav-link" id="nav-tables-tab" data-toggle="tab" href="#nav-tables" role="tab" aria-controls="nav-tables" aria-selected="false">Tables</a>
            <a class="nav-item nav-link" id="nav-itableCatnfo-tab" data-toggle="tab" href="#nav-tableCat" role="tab" aria-controls="nav-tableCat" aria-selected="false">Table Categories</a>
            <a class="nav-item nav-link" id="nav-info-tab" data-toggle="tab" href="#nav-info" role="tab" aria-controls="nav-info" aria-selected="false">Info</a>
            <a class="nav-item nav-link" id="nav-reports-tab" data-toggle="tab" href="#nav-reports" role="tab" aria-controls="nav-reports" aria-selected="false">Reports</a>
            <button class="btn-primary text-align-right" onClick={this.refreshSite}>Refresh</button>
          </div>
        </nav>
        <div class="tab-content" id="nav-tabContent">
          <div class="tab-pane fade show active" id="nav-products" role="tabpanel" aria-labelledby="nav-products-tab"><this.ProductManager /></div>
          <div class="tab-pane fade" id="nav-users" role="tabpanel" aria-labelledby="nav-users-tab"><this.UserManager /></div>
          <div class="tab-pane fade" id="nav-categories" role="tabpanel" aria-labelledby="nav-categories-tab"><this.CategoriesComponent /></div>
          <div class="tab-pane fade" id="nav-tables" role="tabpanel" aria-labelledby="nav-tables-tab"><this.TablesComp /></div>
          <div class="tab-pane fade" id="nav-tableCat" role="tabpanel" aria-labelledby="nav-tableCat-tab"><this.TableCategoyComp /></div>
          <div class="tab-pane fade" id="nav-roles" role="tabpanel" aria-labelledby="nav-roles-tab">...</div>
          <div class="tab-pane fade" id="nav-info" role="tabpanel" aria-labelledby="nav-info-tab"><this.InfoComponent /></div>
          <div class="tab-pane fade" id="nav-reports" role="tabpanel" aria-labelledby="nav-reports-tab"><this.ReportManager /></div>
        </div>
      </div>

    );
  }

  render() {
    return (
      <div>
        {!this.state.loggedIn && <this.loginScreenComp />}
        {this.state.loggedIn && <this.mainDisplay />}
      </div>
    );
  }
}
function App() {
  return (
    <div>
      <Home />
    </div>
  );
}
export default App;
