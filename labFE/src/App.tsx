import Split from "react-split";
import {useEffect, useState} from "react";
import AceEditor from "react-ace";

import "ace-builds/src-noconflict/mode-mysql.js";
import "ace-builds/src-noconflict/theme-monokai.js";

interface QueryDTO {
    id: number,
    description: string
}

interface ProblemDTO {
    name: string,
    description: string,
    author: string,
    schema: string,
    queries: Array<QueryDTO>
}

const App = () => {
    const [problem, setProblem] = useState<ProblemDTO>()

    const getProblem = async (): Promise<ProblemDTO> => {
        const http = await fetch('http://localhost:8080/problem')
        return await http.json()
    }

    useEffect(() => {
        const fetch = async () => {
            setProblem(await getProblem())
        }
        fetch()
    }, [])

    return (
        <Split className='split'>

            <div>
                <h1> {problem?.name} </h1>
                <img src={`http://localhost:8080/static/images/schema.png`} alt="algo xd"/>
                <p> {problem?.queries[0]?.description} </p>
            </div>

            <div>
                <Split className='split-v2' direction='vertical' minSize={45}>
                    <div>
                        <AceEditor
                            mode="mysql"
                            theme="monokai"
                            style={{width: '100%', height: '100%'}}
                            placeholder='Write your sql query here 👇'
                            fontSize={16}
                            setOptions={{
                                enableBasicAutocompletion: false,
                                enableLiveAutocompletion: false,
                                enableSnippets: false,
                                showLineNumbers: true,
                                tabSize: 2,
                            }}
                        />
                    </div>
                    <div>
                        <section style={{display: 'flex', alignItems: 'center', justifyContent: 'space-between', padding: '8px'}}>
                            <h2>Resultados</h2>
                            <div>
                                <button> practicar</button>
                                <button> Enviar </button>
                            </div>
                        </section>
                        <section>
                            <span>Ejecute pruebas para ver el resultado</span>
                        </section>
                    </div>
                </Split>
            </div>
        </Split>

    );
};

export default App;