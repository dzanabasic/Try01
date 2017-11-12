cwlVersion: v1.0
class: CommandLineTool
baseCommand: [single_otu_to_figs.sh]
requirements:
  EnvVarRequirement:
    envDef:
      AGDIR: "/datadrive/"
inputs:
  dir:
    type: string
    inputBinding:
      position: 1

outputs:
  figsdone:
    type: File
    outputBinding:
      glob: "single_otu_to_figs.done"
