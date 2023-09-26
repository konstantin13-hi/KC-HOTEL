import { useState } from 'react'

import './App.css'
import { Route, Routes } from 'react-router-dom'
import IndexPage from './pages/IndexPage'
import LoginPage from './pages/LoginPage'
//yarn dev

function App() {


  return (
    <Routes>
      <Route index element={<IndexPage/>}/>
      <Route path="/login" element={<LoginPage/>}/>
    </Routes>

    
    
  )
}

export default App
