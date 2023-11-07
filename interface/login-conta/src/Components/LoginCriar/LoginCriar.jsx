import React, { useState } from 'react'
import './LoginCriar.css'
import email_icon from '../Assets/email.png'
import password_icon from '../Assets/password.png'

export const LoginCriar = () => {

  const [action, setAction] = useState("Registrar");

  const handleLogin = async () => {
    // Chama a função de login
    await login();
  };

  async function login() {
    let username = document.getElementById("email").value;
    let password = document.getElementById("senha").value;
  
    console.log(username, password);
  
    const response = await fetch("http://localhost:8080/login", {
      method: "POST",
      headers: new Headers({
        "Content-Type": "application/json; charset=utf8",
        Accept: "application/json",
      }),
      body: JSON.stringify({
        username: username,
        password: password,
      }),
    });
  
    let key = "Authorization";
    let token = response.headers.get(key);
    window.localStorage.setItem(key, token);
  
    if (response.ok) {
      showToast("#okToast");
    } else {
      showToast("#errorToast");
    }
  
    window.setTimeout(function () {
      window.location = "/view/index.html";
    }, 2000);
  }
  
  function showToast(id) {
    var toastElList = [].slice.call(document.querySelectorAll(id));
  }

  return (
    <div className='container'>
      <div className="header">
        <div className="texto">Login</div>
        <div className="sublinhado"></div>
      </div>
      <div className="inputs">
        <div className="input">
          <img src={email_icon} alt="" />
          <input type="email" placeholder="E-mail" id="email" />
        </div>
        <div className="input">
          <img src={password_icon} alt="" />
          <input type="password" placeholder="Senha" id="senha" />
        </div>
        <div className="enviar-container">
            <div className={action==="Login"?"enviar cinza":"enviar"} 
            onClick={()=>{setAction("Registrar")}}>Registrar</div>
            <div className={action==="Registrar"?"enviar cinza":"enviar"}
            onClick={handleLogin}>Login</div>
        </div>
      </div>
    </div>
  );
};
