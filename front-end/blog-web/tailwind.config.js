/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./src/**/*.{html,js,jsx,ts,tsx}",  
  ],
  theme: {
    extend: {
      colors: {
        primary: "#F5E5DC",
        secondary: "#B22222",
        tertiary: "#2b2b2b",
        
      },
      fontFamily: {
        roboto: ["Roboto","Poppins", "sans-serif"],
      },
    },
  },
  plugins: [],
}

