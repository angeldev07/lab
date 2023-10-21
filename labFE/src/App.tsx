import Split from "react-split";

const App = () => {
    return (

        <Split className='split'>
            <div>Problem description here</div>
            <div>
                <Split className='split-v2' direction='vertical'>
                    <div>Compiler here</div>
                    <div>Results here </div>
                </Split>
            </div>
        </Split>

    );
};

export default App;