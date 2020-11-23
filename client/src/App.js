import './App.css';
import React, { Component } from "react";
import axios from 'axios';
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
    headers:null,
    username:'',
    password:'',
    loggedIn:false,
    totalNum:0,
    menuState: true,
    cartState: false

  };
  this.categoryClick = this.categoryClick.bind(this);
  this.refreshLst = this.refreshLst.bind(this);
  this.handleAddToCart = this.handleAddToCart.bind(this);
  this.handleCheck = this.handleCheck.bind(this);
  this.handleRemove = this.handleRemove.bind(this);
  this.handleCheckOut = this.handleCheckOut.bind(this);
  this.cartDisp = this.cartDisp.bind(this);
  this.categoryLst = this.categoryLst.bind(this);
  this.productCards = this.productCards.bind(this);
  this.takeOrderComp = this.takeOrderComp.bind(this);
  this.mainMenuComp = this.mainMenuComp.bind(this);
  this.loginScreenComp = this.loginScreenComp.bind(this);
  this.handleLogin = this.handleLogin.bind(this);
  this.handleChange =this.handleChange.bind(this);
  this.menuClicked = this.menuClicked.bind(this);
  this.CartClickedFromMenu = this.CartClickedFromMenu.bind(this);
}

categoryClick(c){
  axios.get(this.url+'/product/view/'+c,{
    headers: this.state.headers
  })
  .then(res => {  
    const prods = res.data;
    this.setState({products:prods });
  });
}

refreshLst(){
  axios.get(this.url +`/product/list`,{
    headers: this.state.headers
  })
  .then(res => {
    const prods = res.data;
    this.setState({products:prods });
  });
  axios.get(this.url + '/product/categories',{
    headers: this.state.headers
  })
  .then(res => {  
    const cats = res.data;
    this.setState({categories:cats });
  });
}

handleAddToCart(v){
  const elem = {
    data:v,
    num:1
  };
  if(this.handleCheck(elem)){
    let elems = [...this.state.cart];  
    let index = elems.findIndex(el => el.data===elem.data);
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
    let index = elems.findIndex(el => el.data===elem.data);
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
  if(this.state.cart.length===1 && v.num===1){
    this.setState({cart:[],totalNum:0,totalPrice:0});
  }
  else if(v.num===1){
    const newList = this.state.cart.filter((item) => item.data !== v.data);
    this.setState({cart:newList,   totalNum:this.state.totalNum-1,
      totalPrice:this.state.totalPrice-v.data.price});
  }else{
    let elems = [...this.state.cart];  
    let index = elems.findIndex(el => el.data===v.data);
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
handleCheckOut(){

  let orders = []
  this.state.cart.map(elem => {
    const order = {
      productId:elem.data.id,
      count:elem.num,
      totalPrice:elem.data.price*elem.num
    };
    orders = [...orders, order]
    return orders;
  });
  axios.post(this.url + "/order/checkout/add",orders,{
    headers: this.state.headers
  })
  .then(() => {
    this.setState({cart:[],
    totalNum:0,
  totalPrice:0})
  })
}

categoryLst(){
  return(
    <div class= "list-group ">
    <span class="border ">
    <button className="btnLst " class="list-group-item list-group-item-action"onClick={this.refreshLst}>All</button>

      {this.state.categories.map(c =>{
       return (<div>
          <button className="btnLst" class="list-group-item list-group-item-action"onClick={() => this.categoryClick(c)}>{c}</button>

          </div>
           );
           }
           )}
      </span>
      </div>
  );
}

productCards(){
return(

  <div  className="OverflowY" class="row border row-cols-1 row-cols-md-2 ">
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

);
}

cartDisp(){
  return(
    <div className="Cart">
    <div class = "row">
    <span class="border ">
      <div className="cartBorder">
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
    </span>
    </div>
    <div class='row'>
    <div class='col'>
      <div className = "TotalTable">
      <div className = 'borderPadding'>
        <div class='row'>
        <span class="border">
          <div className='TotalBorder'>
        Total {this.state.totalNum}
        </div>
        </span>
        </div>
        </div>
        <div className = 'borderPadding'>
        <div class='row'>

        <span class="border">
          <div className='TotalBorder'>
        {this.state.totalPrice} TL
        </div>
        </span>
        </div>
        </div>
      </div>     
    </div>
    <div class='col'>
      <div className = "CheckoutBtn">
      <button class="btn btn-success" onClick={this.handleCheckOut}> Checkout </button>
      </div>
      </div>
    </div>
    </div>
  );
}

takeOrderComp(){

  return(

    <div class="container mt-2">
      <div class="d-flex justify-content-center">
      <div class= "row">
      <div class= "col-sm" className="catBtn">
      <this.categoryLst/>
        </div>
      <div class= "col-sm " className="clList">
      <this.productCards/>
      </div>
      <div  class="col-sm">
      <this.cartDisp/>
</div>
</div>
</div>
</div>

  );
}

CartClickedFromMenu(){
  this.setState({cartState:true,
  menuState: false});
  this.refreshLst();
}

mainMenuComp(){
  return(
    <div class="container m-2  d-flex justify-content-center">
      <div  class="row">
        <div class="col m-2">
          <div class="card" onClick={this.CartClickedFromMenu}>
            <div className="Cartt">
              <div class="card-body ">
                <h5 class="card-text ">Cart</h5>
              </div>
            </div>
          </div>  
        </div>
        <div class="col m-2">
        <div class="card ">
        <div className="Cartt">
          <div class="card-body ">
            <h5 class="card-title">Products</h5>
            </div>
            </div>
          </div>
        </div> 
        <div class="col m-2">
        <div class="card ">
        <div className="Cartt">
          <div class="card-body">
            <h5 class="card-title">...</h5>
            </div>
            </div>
          </div>
        </div> 
        </div>
        <div  class="row">
        <div class="col m-2">
        <div class="card  ">
        <div className="Cartt">
          <div class="card-body">
            <h5 class="card-title">Tables</h5>
            </div>
            </div>
          </div>
        </div> 
        <div class="col m-2">
        <div class="card  ">
        <div className="Cartt">
          <div class="card-body">
            <h5 class="card-title">Users</h5>
            </div>
            </div>
          </div>
        </div> 
        <div class="col m-2">
        <div class="card  ">
        <div className="Cartt">
          <div class="card-body">
            <h5 class="card-title">...</h5>
            </div>
            </div>
          </div>
        </div> 
        </div>
        <div  class="row">
        <div class="col m-2">
        <div class="card  ">
        <div className="Cartt">
          <div class="card-body">
            <h5 class="card-title">Reports</h5>
            </div>
            </div>
          </div>
        </div> 
        <div class="col m-2">
        <div class="card ">
        <div className="Cartt">
          <div class="card-body">
            <h5 class="card-title">...</h5>
            </div>
            </div>
          </div>
        </div> 
        <div class="col m-2">
        <div class="card ">
        <div className="Cartt">
          <div class="card-body">
            <h5 class="card-title">Logout</h5>
          </div>
          </div>
        </div> 
        </div>
      </div>
    </div>
  );
}

handleChange(event) {
  const target = event.target;
  const name = target.name;
  const value = target.value;
  
  this.setState({
    [name]: value
  });

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
  axios.get(this.url +`/product/list`,{
    headers: this.state.headers
  })
  .then(res => {
    const prods = res.data;
    this.setState({products:prods });
  });
  axios.get(this.url + '/product/categories',{
    headers: this.state.headers
  })
  .then(res => {  
    const cats = res.data;
    this.setState({categories:cats });
  });

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

menuClicked(){
  this.setState({
    menuState:true,
    cartState:false
  })
}

render(){
  return(
    <div>
      <div class="pos-right">
      <nav class="navbar navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" onClick={this.menuClicked}>
      <span class="navbar-toggler-icon"></span>
    </button>
    </nav>
    </div>
    {!this.state.loggedIn &&<this.loginScreenComp/>}
    {this.state.loggedIn && this.state.menuState && <this.mainMenuComp/>}
    {this.state.loggedIn && this.state.cartState && <this.takeOrderComp/>}
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
