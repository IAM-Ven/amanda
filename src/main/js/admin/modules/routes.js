import React from 'react'
import { Route, IndexRoute } from 'react-router'
import App from './App'
import Home from './home/Home'
import Monitor from './monitor/Monitor'
import Articles from './article/Articles'
import Article from './article/Article'
import CreateArticle from './article/CreateArticle'
import Categories from './category/Categories'
import FileService from './file/FileService'
import Report from './Report'
import Analyze from './Analyze'
import Profile from './profile/Profile'
import Settings from './settings/Settings'

const routes = (
    <Route path="/" component={App}>
        <IndexRoute component={Home}/>
        <Route path="/articles" component={Articles}>
        </Route>
        <Route path="/articles/:articleId/:articleName" component={Article}/>
        <Route path="/articles/new" component={CreateArticle}/>
        <Route path="/categories" component={Categories}/>
        <Route path="/files" component={FileService}>
             <Route path="/files/"/>
        </Route>
        <Route path="/report" component={Report}/>
        <Route path="/analyze" component={Analyze}/>
        <Route path="/profile" component={Profile}/>
        <Route path="/monitor" component={Monitor}/>
        <Route path="/settings" component={Settings}/>
    </Route>
);

export default routes;


