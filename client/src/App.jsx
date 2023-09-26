import { useState } from 'react'

import './App.css'
import { Route, Routes } from 'react-router-dom'
import IndexPage from './pages/IndexPage'
import LoginPage from './pages/LoginPage'
import Layout from './Layout'
//yarn dev

function App() {


  return (
    <Routes>
      <Route path="/" element={<Layout />}> 
      <Route index element={<IndexPage/>}/>
      <Route path="/login" element={<LoginPage/>}/>

      </Route>

    </Routes>

    
    
  )
}

export default App
