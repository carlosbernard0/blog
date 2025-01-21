import { createContext, useState } from "react"
import PropTypes from "prop-types"

export const ApplicationContext = createContext()

export const ApplicationContextProvider = ({children}) => {
    const [token, setToken] = useState('')

    return (
        <ApplicationContext.Provider value={{
            token, setToken
        }}>
            {children}
        </ApplicationContext.Provider>
        
    
    )
}

ApplicationContextProvider.propTypes = {
    children: PropTypes.node.isRequired,  
};