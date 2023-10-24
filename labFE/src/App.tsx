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
    const [value, setValue] = useState('SELECT * FROM courses')
    const [result, setResult] = useState<any>()
    const [correct, setCorrect] = useState< >(false)

    const getProblem = async (): Promise<ProblemDTO> => {
        const http = await fetch('http://localhost:8080/problem')
        return await http.json()
    }

    const getResults = async () => {
        const http = await  fetch(`http://localhost:8080/problem/execute?query=${value}&problemId=${problem?.queries[0].id}`)
        const res = await http.json()
        setResult(res)
    }

    const validateQuery = async () => {
        if(! result) {
            console.log('here?')
            return
        }
        const http = await  fetch(`http://localhost:8080/problem/validate`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                results: [...result],
                problemId: problem?.queries[0].id
            })

        })
        const res = await http.json();
        
        setCorrect(res)
    }


    useEffect(() => {
        const fetch = async () => {
            setProblem(await getProblem())
        }
        fetch()
    }, [])

    useEffect(() => {
        console.log(result)
    }, [result]);

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
                            value={value}
                            onChange={ (value) => setValue(value)}
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
                    <div style={{backgroundColor: "#272822", color: "#fff", padding: '0 1rem'}}>
                        <section style={{display: 'flex', alignItems: 'center', justifyContent: 'space-between', padding: '8px'}}>
                            <h2>Resultados</h2>
                            <div>
                                <button onClick={getResults}> practicar</button>
                                <button onClick={validateQuery}> Enviar </button>
                            </div>
                        </section>
                        <section>
                            {! result && <span>Ejecute pruebas para ver el resultado</span>}
                            {correct && <p>esta bien perro </p>}
                            {result && (
                                <table className="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            {
                                               Object.keys(result[0]).map((column,i) => (
                                                   <th key={i}>{column}</th>
                                               ))
                                            }
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {
                                            result.map((res, i) => (
                                                <tr key={i+1}>
                                                    {
                                                        Object.values(res).map(value => (
                                                            <td key={value}>{value}</td>
                                                        ))
                                                    }
                                                </tr>
                                            ) )
                                        }
                                    </tbody>
                                </table>
                            )}
                        </section>
                    </div>
                </Split>
            </div>
        </Split>

    );
};

export default App;