cwlVersion: v1.0
class: CommandLineTool
baseCommand: [single_gz_to_otu.sh]
inputs:
  dir:
    type: string
    inputBinding:
      position: 1
outputs:
  otudone:
    type: File
    outputBinding:
      glob: "single_gz_to_otu.done"