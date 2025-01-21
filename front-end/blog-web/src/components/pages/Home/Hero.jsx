
export const Hero = () => {

  return (
    <div className="bg-primary h-96 font-roboto flex items-center">
        <div className="flex flex-col gap-6 p-4 pt-10 ml-12 mr-3 w-full sm:w-3/4 md:w-1/2 lg:w-2/5 ">
            <h1 className="text-4xl font-bold ">
                Discover Your Next Favorite Read Today!
            </h1>
            <p className="text-xl">
                Welcome to our blog, where passion meets creativity. Dive into a world of engaging content tailored just for you!
            </p>
            <div className="flex gap-2">
                    <button className="p-3 bg-secondary text-primary">
                        Explore
                    </button>
                    <button className="p-3 bg-primary text-secondary border border-secondary hover:bg-secondary hover:text-primary">
                        Join
                    </button>
            </div>
        </div>
    </div>
  )
}
