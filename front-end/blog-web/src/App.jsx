import { ApplicationContextProvider } from "./context/ApplicationContext"
import { Home } from "./components/pages/Home/Home"
import { createBrowserRouter, RouterProvider } from 'react-router'


const router = createBrowserRouter(
  [
    {
      path:  '/',
      element: <Home/>
    }
  ]
)

function App() {

  return (
    <ApplicationContextProvider>
      <RouterProvider router={router}/>
    </ApplicationContextProvider>
  )
}

export default App
