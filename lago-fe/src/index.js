import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter, Route, Link} from 'react-router-dom';

/*Stylesheets*/
import './Style/index.css';
import 'bootstrap/dist/css/bootstrap.min.css';

/*Main Componets*/
import App from './App';


/*Render and router*/
ReactDOM.render(
  
    <BrowserRouter>
        <App/>
    </BrowserRouter>,
    document.getElementById('root')
);
