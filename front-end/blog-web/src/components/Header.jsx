import { useContext } from "react"
import { ApplicationContext } from "../context/ApplicationContext"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';





export const Header = () => {
    const { token } = useContext(ApplicationContext)


    const navLinks = [
        {name: 'home', path: '/', title : 'Home' },
        {name: 'post', path: '/posts', title : 'My Posts' },
        {name: 'comments', path: '/comments', title : 'My Comments' },
    ]

    

    return (
        <div className="bg-tertiary p-1 flex items-center justify-between text-primary font-roboto">
            <div className="flex flex-row gap-3 items-center ml-10">
                <img src="/src/assets/images/logoOutBg.png" className="w-[70px] h-[70px]" alt="logo for blog" />
                <h1 className="text-2xl cursor-pointer">
                    Blogged
                </h1>
            </div>
            {/* <div>
                <ul className="flex justify-evenly gap-4">
                    {token == "" ? (
                        <li className=" hover:text-secondary"><a href={navLinks[0].path}>
                            {navLinks[0].title}    
                        </a></li>
                    ):
                    (
                        navLinks.map((navLink)=> (
                            
                                <li className="hover:text-secondary" key={(navLink) => navLink.name}>
                                    <a href={navLink.path} >
                                        {navLink.title}
                                    </a>
                                </li>
                        ))
                    )}
                </ul>
            </div> */}
            {token == '' ? (
                <div className="flex flex-row gap-4 mr-10">
                    <button className="bg-tertiary text-secondary border border-secondary p-1 hover:bg-secondary hover:text-primary">
                        <p className="p-1">
                            Log In
                        </p>
                    </button>
                    <button className="bg-secondary text-primary p-1 hover:bg-tertiary hover:text-secondary">
                        <p className="p-1">
                            Sign Up
                        </p>
                    </button>
                </div>

            ): (
                <div className=" flex flex-row gap-4 mr-10">
                    <FontAwesomeIcon icon={faUser} className="w-6 h-6" />
                </div>

            )}
        </div>
    )
}
